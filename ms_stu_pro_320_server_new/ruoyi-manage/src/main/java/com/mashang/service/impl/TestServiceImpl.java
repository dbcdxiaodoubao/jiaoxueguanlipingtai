package com.mashang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mashang.comming.TestMapping;
import com.mashang.constant.MessageConstant;
import com.mashang.constant.TestType;
import com.mashang.domain.entity.Test;
import com.mashang.domain.entity.TestAnswer;
import com.mashang.domain.entity.TestClass;
import com.mashang.domain.query.common.PageQuery;
import com.mashang.domain.query.management.QuestionTestCreat;
import com.mashang.domain.query.management.TestListQuery;
import com.mashang.domain.query.student.*;
import com.mashang.domain.vo.management.ManageTestListVo;
import com.mashang.domain.vo.management.TestDtlVo;
import com.mashang.domain.vo.student.QuestionAnswerVo;
import com.mashang.domain.vo.student.TestAnswerInfo;
import com.mashang.domain.vo.student.TestListVo;
import com.mashang.domain.vo.student.VideoTestVo;
import com.mashang.mapper.*;
import com.mashang.service.IQuestionAnswerService;
import com.mashang.service.ITestService;
import com.mashang.util.SubjectUtils;
import com.mashang.util.TestUtils;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 20413
 * @description 针对表【ms_test(试卷表)】的数据库操作Service实现
 * @createDate 2025-10-22 18:01:40
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test>
        implements ITestService {

    @Autowired
    private TestMapper testMapper;

    @Autowired
    private TestClassMapper testClassMapper;

    @Autowired
    private TestMapping testMapping;

    @Autowired
    private SubjectUtils subjectUtils;

    /**
     * 查询学生所有的答卷列表
     *
     * @param pageQuery     分页条件
     * @param testPageQuery 试卷的分页条件 类型和学科
     * @return 学生所有的试卷列表
     */
    @Override
    public Page<TestListVo> pageStudentTests(PageQuery pageQuery, TestPageQuery testPageQuery, Long userId) {
        if (!subjectUtils.checkSubject(testPageQuery.getSubjectId())) {
            throw new ServiceException(MessageConstant.SUBJECT_NOT_CORRESPOND_TO_GRADE);
        }
        if (!TestUtils.isNeed(testPageQuery.getTestType())) {
            throw new ServiceException("只能选择 1、固定试卷 2、时段试卷 5、班级试卷");
        }
        Page<TestListVo> testListVoPage = PageHelper.startPage(pageQuery);
        baseMapper.pageStudentTests(testPageQuery, userId);
        return testListVoPage;
    }

    /**
     * 查询学生所有视频答卷列表
     *
     * @param pageQuery          分页参数
     * @param videoTestPageQuery 视频查询条件
     * @return 学生所有视频答卷列表
     */
    @Override
    public Page<VideoTestVo> pageVideoTests(PageQuery pageQuery, VideoTestPageQuery videoTestPageQuery) {
        if (!subjectUtils.checkSubject(videoTestPageQuery.getSubjectId())) {
            throw new ServiceException(MessageConstant.SUBJECT_NOT_CORRESPOND_TO_GRADE);
        }
        Page<VideoTestVo> videoTestVoPage = PageHelper.startPage(pageQuery);
        baseMapper.pageVideoTests(videoTestPageQuery);
        return videoTestVoPage;
    }

    @Override
    public Integer haveTest(Integer subjectId) {
        return testMapper.haveTest(subjectId);
    }

    @Override
    public List<ManageTestListVo> list(TestListQuery testListQuery) {
        return testMapper.list(testListQuery);
    }

    @Override
    public TestDtlVo dtl(Integer testId) {
        return testMapper.dtl(testId);
    }

    @Override
    public Integer linkTestQuestion(Integer testId, QuestionTestCreat questionTestCreat) {
        return testMapper.linkTestQuestion(testId, questionTestCreat);
    }

    @Override
    public Integer breakTestQuestion(Integer testId) {
        return testMapper.breakTestQuestion(testId);
    }

    @Override
    public Integer haveTestAnswer(Integer testId) {
        return testMapper.haveTestAnswer(testId);
    }

    /**
     * 教师端查询试卷列表
     *
     * @param query
     * @return
     */
    @Override
    public List<com.mashang.domain.vo.teacher.TestListVo> pageQueryTeacher(com.mashang.domain.query.teacher.TestPageQuery query) {
        return baseMapper.pageQueryTeacher(new Page<Test>(query.getPageNum(), query.getPageSize()), query,
                SecurityUtils.getLoginUser().getUser().getGrade());
    }

    /**
     * 教师端修改试卷
     *
     * @param test
     * @param classIds
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Test test, List<Integer> classIds) {
        Integer testId = test.getTestId();
        testMapper.updateById(test);
        testClassMapper.delete(new LambdaQueryWrapper<TestClass>().eq(TestClass::getTestId, testId));
        for (Integer classId : classIds) {
            testClassMapper.insert(new TestClass().setTestId(testId).setClassId(classId));
        }
    }

    /**
     * 教师端查询试卷详情
     *
     * @param testId
     * @return
     */
    @Override
    public com.mashang.domain.vo.teacher.TestDtlVo getById(Integer testId) {
        com.mashang.domain.vo.teacher.TestDtlVo testDtlVo = testMapping.toTestDtlVo(testMapper.selectById(testId));
        List<Object> list = testClassMapper.selectObjs(new LambdaQueryWrapper<TestClass>()
                .select(TestClass::getClassId).eq(TestClass::getTestId, testId));
        testDtlVo.setClassIds(list.stream().map(Integer.class::cast).collect(Collectors.toList()));
        return testDtlVo;
    }

    /**
     * 教师端添加试卷
     *
     * @param test
     * @param classIds
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(Test test, List<Integer> classIds) {
        baseMapper.insert(test);
        for (Integer classId : classIds) {
            testClassMapper.insert(new TestClass().setTestId(test.getTestId()).setClassId(classId));
        }
    }


}




