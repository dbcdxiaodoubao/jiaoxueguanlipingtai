package com.mashang.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.mashang.comming.QuestionAnswerMapping;
import com.mashang.comming.TestAnswerMapping;
import com.mashang.constant.MessageConstant;
import com.mashang.constant.StatusConstant;
import com.mashang.domain.entity.QuestionAnswer;
import com.mashang.domain.entity.Test;
import com.mashang.domain.entity.TestAnswer;
import com.mashang.domain.query.manage.TestAnswerPageQuery;
import com.mashang.domain.entity.*;
import com.mashang.domain.query.common.PageQuery;
import com.mashang.domain.query.student.QuestionSubmit;
import com.mashang.domain.query.student.TestRecordQuery;
import com.mashang.domain.query.student.TestSubmit;
import com.mashang.domain.vo.management.TestAnswerDtlVo;
import com.mashang.domain.vo.management.TestAnswerListVo;
import com.mashang.domain.vo.student.QuestionAnswerVo;
import com.mashang.domain.vo.student.TestAnswerInfo;
import com.mashang.domain.vo.student.TestListVo;
import com.mashang.domain.vo.student.VideoTestVo;
import com.mashang.mapper.QuestionAnswerMapper;
import com.mashang.mapper.TestMapper;
import com.mashang.domain.vo.student.*;
import com.mashang.mapper.*;
import com.mashang.service.IQuestionAnswerService;
import com.mashang.service.ITestAnswerService;
import com.mashang.util.QuestionUtils;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 20413
 * @description 针对表【ms_test_answer(答卷表)】的数据库操作Service实现
 * @createDate 2025-10-22 18:01:40
 */
@Service
public class TestAnswerServiceImpl extends ServiceImpl<TestAnswerMapper, TestAnswer>
        implements ITestAnswerService {

    @Autowired
    private QuestionAnswerMapper questionAnswerMapper;
    @Autowired
    private IQuestionAnswerService questionAnswerService;
    @Autowired
    private TestMapper testMapper;
    @Autowired
    private TestAnswerMapper testAnswerMapper;
    @Autowired
    private RandomTestMapper randomTestMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 查询学生未做完的答卷列表
     *
     * @param userId 学生id
     * @return 学生未做完的试卷列表
     */
    @Override
    public List<TestListVo> getStudentTests(Long userId) {
        return baseMapper.getStudentTests(userId);
    }

    /**
     * 根据答卷id查询答卷详情
     *
     * @param testAnswerId 答卷id
     * @return 答卷详情
     */
    @Override
    public TestAnswerInfo getStudentTestInfo(Long testAnswerId) {
        TestAnswer testAnswer = baseMapper.selectById(testAnswerId);
        if (testAnswer == null) {
            throw new ServiceException(MessageConstant.ANSWER_SHEET_NOT_EXIST);
        }
        //组装考试信息
        TestAnswerInfo result = TestAnswerMapping.INSTANCE.toTestAnswerInfo(testAnswer);
        if (testAnswer.getTestId() != null) {
            Test test = testMapper.selectById(testAnswer.getTestId());
            result.setSuggestDuration(test.getSuggestDuration());
            result.setQuestionNum(test.getQuestionNum());
        } else if (testAnswer.getRandomTestId() != null) {
            RandomTest randomTest = randomTestMapper.selectById(testAnswer.getRandomTestId());
            result.setQuestionNum(randomTest.getQuestionNum());
        } else {
            throw new ServiceException(MessageConstant.SHEET_NOT_EXIST);
        }

        //组装题目
        LambdaQueryWrapper<QuestionAnswer> qlqw = Wrappers.lambdaQuery();
        qlqw.eq(QuestionAnswer::getTestAnswerId, testAnswerId);
        List<QuestionAnswer> questionAnswers = questionAnswerMapper.selectList(qlqw);
        if (CollUtil.isEmpty(questionAnswers)) {
            throw new ServiceException(MessageConstant.QUESTION_NOT_EXIST);
        }
        List<QuestionAnswerVo> questionAnswerVoList = QuestionAnswerMapping.INSTANCE.toQuestionAnswerVoList(questionAnswers);
        result.setQuestionVos(questionAnswerVoList);

        //如果是答题的查询，则不能显示答案
        if (result.getStatus() == null || result.getStatus().equals(StatusConstant.EXAM_PAPER_STATUS_INCOMPLETE)) {
            result.setDuration(null);
            result.setUserTestScore(null);
            for (QuestionAnswerVo questionVo : result.getQuestionVos()) {
                questionVo.setStatus(null);
                questionVo.setUserAnswer(null);
                questionVo.setRightAnswer(null);
            }
        }
        return result;
    }

    /**
     * 提交试卷
     *
     * @param testSubmit 学生写完的试卷
     * @return 影响行数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer submitTest(TestSubmit testSubmit) {
        //先改题目
        List<QuestionSubmit> questionSubmits = testSubmit.getQuestionSubmits();
        Integer userScore = 0;
        boolean flag = false;
        for (QuestionSubmit questionSubmit : questionSubmits) {
            if (!QuestionUtils.isObjective(questionSubmit.getQuestionType())) {
                questionSubmit.setStatus(StatusConstant.ANSWER_STATUS_PENDING);
                flag = true;
                continue;
            }
            String userAnswer = questionSubmit.getUserAnswer();
            QuestionAnswer questionAnswer = questionAnswerMapper.selectById(questionSubmit.getQuestionAnswerId());
            //获取正确答案 可待优化
            String rightAnswer = questionAnswer.getRightAnswer();
            if (checkRightOrError(userAnswer, rightAnswer)) {
                questionSubmit.setStatus(StatusConstant.ANSWER_STATUS_CORRECT);
                userScore += questionAnswer.getQuestionScore();
            } else {
                questionSubmit.setStatus(StatusConstant.ANSWER_STATUS_WRONG);
            }
        }

        //将题目转换为可加入类型
        List<QuestionAnswer> questionAnswerList = QuestionAnswerMapping.INSTANCE.toQuestionAnswer(questionSubmits);
        questionAnswerService.updateBatchById(questionAnswerList);

        //后改答卷
        TestAnswer testAnswer = TestAnswerMapping.INSTANCE.toTestAnswer(testSubmit);
        if (flag) {
            testAnswer.setStatus(StatusConstant.EXAM_PAPER_STATUS_PENDING);
        } else {
            testAnswer.setStatus(StatusConstant.EXAM_PAPER_STATUS_COMPLETED);
        }
        testAnswer.setUserTestScore(userScore);
        testAnswer.setSubmitTime(new Date());
        return baseMapper.updateById(testAnswer);
    }

    /**
     * 查询未完成的视频试卷
     *
     * @return 视频试卷列表
     */
    @Override
    public List<VideoTestVo> getVideoTests(Long userId) {
        return testAnswerMapper.getVideoTests(userId);
    }


    /**
     * 创建随机答卷
     *
     * @param randomTestId 随机答卷id
     * @return 影响行数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer createRandomTest(Long randomTestId) {
        Long userId = SecurityUtils.getUserId();
        //创造答卷
        RandomTest randomTest = randomTestMapper.selectById(randomTestId);
        TestAnswer testAnswer = TestAnswerMapping.INSTANCE.toTestAnswer(randomTest);
        testAnswer.setStatus(StatusConstant.EXAM_PAPER_STATUS_INCOMPLETE);
        int result = baseMapper.insert(testAnswer);
        //创造答题
        Long testAnswerId = testAnswer.getTestAnswerId();
        List<Question> questions = questionMapper.selectListByRandomTestId(randomTestId);

        List<QuestionAnswer> questionAnswerList = QuestionAnswerMapping.INSTANCE.toQuestionAnswerList(questions);
        for (int i = 0; i < questionAnswerList.size(); i++) {
            System.out.println(questions.get(i));
            questionAnswerList.get(i).setUserId(userId);
            questionAnswerList.get(i).setTestAnswerId(testAnswerId);
            questionAnswerList.get(i).setOption(questions.get(i).getOption());
        }
        questionAnswerService.saveBatch(questionAnswerList);
        return result;
    }


    /**
     * 查询随机试卷详情
     *
     * @param randomTestId 随机试卷id
     * @return 随机试卷详情
     */
    @Override
    public TestAnswerInfo getRandomInfo(Long randomTestId) {
        TestAnswer testAnswer = getTestAnswerByRandomTestId(randomTestId);
        return getStudentTestInfo(testAnswer.getTestAnswerId());
    }


    /**
     * 根据提交时间倒叙分页查询该学生做过的试卷基本信息
     * @param pageQuery 分页条件
     * @param testRecordQuery 查询条件
     * @return 学生做过的试卷基本信息
     */
    @Override
    public com.github.pagehelper.Page<TestRecordListVo> listTestRecord(PageQuery pageQuery, TestRecordQuery testRecordQuery) {
        com.github.pagehelper.Page<TestRecordListVo> page = PageHelper.startPage(pageQuery);
        baseMapper.listTestRecord(testRecordQuery);
        return page;
    }


    private TestAnswer getTestAnswerByRandomTestId(Long randomTestId) {
        LambdaQueryWrapper<TestAnswer> tlqw = Wrappers.lambdaQuery();
        tlqw.eq(TestAnswer::getRandomTestId, randomTestId);
        tlqw.eq(TestAnswer::getStatus, StatusConstant.EXAM_PAPER_STATUS_INCOMPLETE);
        TestAnswer testAnswer = baseMapper.selectOne(tlqw);
        if (testAnswer == null) {
            throw new ServiceException(MessageConstant.ANSWER_SHEET_NOT_EXIST);
        }
        return testAnswer;
    }

    private boolean checkRightOrError(String studentAnswer, String rightAnswer) {
        studentAnswer = studentAnswer.toLowerCase();
        rightAnswer = rightAnswer.toLowerCase();
        String[] user = studentAnswer.split(",");
        String[] right = rightAnswer.split(",");
        if (user.length != right.length) {
            return false;
        }
        Set<String> set = new HashSet<>();
        int length = right.length;
        for (int i = 0; i < length; i++) {
            set.add(user[i]);
            set.add(right[i]);
        }
        return set.size() == length;
    }
    /**
     * 获取答卷列表
     * @param pageQuery 分页参数
     * @return 答卷列表
     */
    @Override
    public List<TestAnswerListVo> testAnswerlist(TestAnswerPageQuery pageQuery,Integer status) {
        List<Long> userIds = null;
        if (StringUtils.isNotNull(pageQuery.getClassId())){
            //查出班级学生id集合
            LambdaQueryWrapper<SysUser> UserQueryWrapper = new LambdaQueryWrapper<SysUser>()
                    .eq(StringUtils.isNotNull(pageQuery.getClassId()), SysUser::getClassId, pageQuery.getClassId())
                    .select(SysUser::getUserId);
            userIds = sysUserMapper.selectObjs(UserQueryWrapper).stream()
                    .map(obj -> (Long) obj)
                    .collect(Collectors.toList());
        }

        return baseMapper.testAnswerList(new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize())
                ,pageQuery.getSubjectId(),userIds,status);
    }

    /**
     * 获取答卷详情
     * @param testAnswerId 答卷id
     * @return
     */
    @Override
    public TestAnswerDtlVo getTestAnswerInfo(Long testAnswerId) {
        return baseMapper.getTestAnswerInfo(testAnswerId);
    }

    /**
     * 自动批改
     * @param testAnswerId 答卷id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void submitAutoCorrect(Long testAnswerId) {
        LambdaUpdateWrapper<QuestionAnswer> uqwCorrect = new LambdaUpdateWrapper<QuestionAnswer>()
                .eq(QuestionAnswer::getTestAnswerId, testAnswerId)
                .apply("user_answer = right_answer")
                .set(QuestionAnswer::getStatus, StatusConstant.ANSWER_STATUS_CORRECT)
                .setSql("user_question_score = question_score");
        questionAnswerService.update(uqwCorrect);

        LambdaUpdateWrapper<QuestionAnswer> uqwError = new LambdaUpdateWrapper<QuestionAnswer>()
                .eq(QuestionAnswer::getTestAnswerId, testAnswerId)
                .apply("user_answer != right_answer")
                .set(QuestionAnswer::getStatus, StatusConstant.ANSWER_STATUS_WRONG);
        questionAnswerService.update(uqwError);
    }

    /**
     * 手动批改主观题
     * @param questionAnswerList 答题列表
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void correct(List<QuestionAnswer> questionAnswerList) {
        for (QuestionAnswer questionAnswer : questionAnswerList) {
            LambdaUpdateWrapper<QuestionAnswer> uqw = new LambdaUpdateWrapper<QuestionAnswer>()
                    .eq(QuestionAnswer::getQuestionAnswerId, questionAnswer.getQuestionAnswerId())
                    .set(QuestionAnswer::getStatus, questionAnswer.getStatus())
                    .set(QuestionAnswer::getUserQuestionScore,questionAnswer.getUserQuestionScore());
            questionAnswerService.update(uqw);
        }
    }

}




