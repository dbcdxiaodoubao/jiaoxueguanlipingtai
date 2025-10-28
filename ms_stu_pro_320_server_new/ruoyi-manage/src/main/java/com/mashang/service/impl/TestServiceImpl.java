package com.mashang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mashang.domain.entity.Test;
import com.mashang.domain.entity.TestAnswer;
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
import com.mashang.mapper.QuestionAnswerMapper;
import com.mashang.mapper.TestAnswerMapper;
import com.mashang.mapper.TestQuestionMapper;
import com.mashang.service.IQuestionAnswerService;
import com.mashang.service.ITestService;
import com.mashang.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

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

    /**
     * 查询学生所有的答卷列表
     *
     * @param pageQuery     分页条件
     * @param testPageQuery 试卷的分页条件 类型和学科
     * @return 学生所有的试卷列表
     */
    @Override
    public Page<TestListVo> pageStudentTests(PageQuery pageQuery, TestPageQuery testPageQuery) {
        Page<TestListVo> testListVoPage = PageHelper.startPage(pageQuery);
        baseMapper.pageStudentTests(testPageQuery);
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


}




