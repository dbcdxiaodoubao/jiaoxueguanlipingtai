package com.mashang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mashang.comming.QuestionAnswerMapping;
import com.mashang.comming.TestAnswerMapping;
import com.mashang.constant.QuestionType;
import com.mashang.constant.StatusConstant;
import com.mashang.domain.entity.Question;
import com.mashang.domain.entity.QuestionAnswer;
import com.mashang.domain.entity.Test;
import com.mashang.domain.entity.TestAnswer;
import com.mashang.domain.query.student.QuestionSubmit;
import com.mashang.domain.query.student.TestSubmit;
import com.mashang.domain.vo.student.QuestionAnswerVo;
import com.mashang.domain.vo.student.TestAnswerInfo;
import com.mashang.domain.vo.student.TestListVo;
import com.mashang.domain.vo.student.VideoTestVo;
import com.mashang.mapper.QuestionAnswerMapper;
import com.mashang.mapper.QuestionMapper;
import com.mashang.mapper.TestAnswerMapper;
import com.mashang.service.IQuestionAnswerService;
import com.mashang.service.ITestService;
import com.mashang.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    private TestAnswerMapper testAnswerMapper;
    @Autowired
    private QuestionAnswerMapper questionAnswerMapper;
    @Autowired
    private IQuestionAnswerService questionAnswerService;

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
        TestAnswer testAnswer = testAnswerMapper.selectById(testAnswerId);
        Integer testId = testAnswer.getTestId();
        //查询试卷
        Test test = baseMapper.selectById(testId);
        //试卷信息
        TestAnswerInfo result = TestAnswerMapping.INSTANCE.toTestAnswerInfo(test);
        result.setTestAnswerId(testAnswerId);
        result.setSumScore(testAnswer.getSumScore());

        //组装题目
        LambdaQueryWrapper<QuestionAnswer> qlqw = Wrappers.lambdaQuery();
        qlqw.eq(QuestionAnswer::getTestAnswerId, testAnswerId);
        List<QuestionAnswer> questionAnswers = questionAnswerMapper.selectList(qlqw);
        List<QuestionAnswerVo> questionAnswerVoList = QuestionAnswerMapping.INSTANCE.toQuestionAnswerVoList(questionAnswers);
        result.setQuestionVos(questionAnswerVoList);
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
        for (QuestionSubmit questionSubmit : questionSubmits) {
            if (questionSubmit.getQuestionType().equals(QuestionType.SUBJECTIVE)) {
                questionSubmit.setStatus(StatusConstant.ANSWER_STATUS_PENDING);
                continue;
            }
            String userAnswer = questionSubmit.getUserAnswer();
            QuestionAnswer questionAnswer = questionAnswerMapper.selectById(questionSubmit.getQuestionAnswerId());
            //获取正确答案 可待优化
            String rightAnswer = questionAnswer.getRightAnswer();
            if (userAnswer.equalsIgnoreCase(rightAnswer)) {
                questionSubmit.setStatus(StatusConstant.ANSWER_STATUS_CORRECT);
                userScore+=questionAnswer.getQuestionScore();
            } else {
                questionAnswer.setStatus(StatusConstant.ANSWER_STATUS_WRONG);
            }
        }

        //将题目转换为可加入类型
        List<QuestionAnswer> questionAnswerList = QuestionAnswerMapping.INSTANCE.toQuestionAnswer(questionSubmits);
        questionAnswerService.updateBatchById(questionAnswerList);

        //后改试卷
        TestAnswer testAnswer = TestAnswerMapping.INSTANCE.toTestAnswer(testSubmit);
        testAnswer.setStatus(StatusConstant.EXAM_PAPER_STATUS_PENDING);
        testAnswer.setUserTestScore(userScore);
        return testAnswerMapper.updateById(testAnswer);
    }
    /**
     * 查询未完成的视频试卷
     * @return 视频试卷列表
     */
    @Override
    public List<VideoTestVo> getVideoTests(Long userId) {
        return testAnswerMapper.getVideoTests(userId);
    }
}




