package com.mashang.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mashang.constant.MessageConstant;
import com.mashang.constant.QuestionType;
import com.mashang.constant.RedisConstant;
import com.mashang.constant.TestType;
import com.mashang.domain.entity.*;
import com.mashang.domain.query.common.PageQuery;
import com.mashang.domain.query.student.RandomTestQuery;
import com.mashang.domain.vo.student.RandomTestVo;
import com.mashang.domain.vo.student.SubjectsListByGradeVo;
import com.mashang.mapper.QuestionMapper;
import com.mashang.mapper.RandomTestQuestionMapper;
import com.mashang.mapper.TestQuestionMapper;
import com.mashang.service.IRandomTestQuestionService;
import com.mashang.service.IRandomTestService;
import com.mashang.mapper.RandomTestMapper;
import com.mashang.service.ISubjectsService;
import com.mashang.util.SubjectUtils;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 20413
 * @description 针对表【ms_random_test】的数据库操作Service实现
 * @createDate 2025-10-25 11:54:19
 */
@Service
public class RandomTestServiceImpl extends ServiceImpl<RandomTestMapper, RandomTest>
        implements IRandomTestService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private IRandomTestQuestionService randomTestQuestionService;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private SubjectUtils subjectUtils;

    /**
     * 自动生成随机试卷
     *
     * @param randomTestQuery 生成随机试卷的限制条件
     * @return 影响行数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer produceRandomTest(RandomTestQuery randomTestQuery) {
        if (!subjectUtils.checkSubject(randomTestQuery.getSubjectId())) {
            throw new ServiceException(MessageConstant.SUBJECT_NOT_CORRESPOND_TO_GRADE);
        }
        //判断题
        Integer judgmentNum = randomTestQuery.getJudgmentNum();
        //多选题
        Integer multipleNum = randomTestQuery.getMultipleNum();
        //单选题
        Integer singleNum = randomTestQuery.getSingleNum();
        //学科
        Long subjectId = randomTestQuery.getSubjectId();

        if (judgmentNum == 0 && multipleNum == 0 && singleNum == 0) {
            throw new ServiceException(MessageConstant.QUESTION_NOT_EXIST);
        }
        //选取题目
        LambdaQueryWrapper<Question> qlqw = Wrappers.lambdaQuery();
        qlqw.eq(Question::getQuestionDifficulty, randomTestQuery.getQuestionDifficult());
        qlqw.eq(Question::getSubjectId, subjectId);
        List<Question> questions = questionMapper.selectList(qlqw);
        if (CollUtil.isEmpty(questions)) {
            throw new ServiceException(MessageConstant.QUESTION_TOO_LESS);
        }
        //通过题目类型进行分组
        Map<Integer, List<Question>> collect = questions.stream().collect(Collectors.groupingBy(Question::getQuestionType));
        //获取随机题目
        List<Question> associatedQuestions = new ArrayList<>();
        associatedQuestions.addAll(getRandomQuestions(collect.get(QuestionType.SINGLE_CHOICE), singleNum, QuestionType.CN_SINGLE_CHOICE));
        associatedQuestions.addAll(getRandomQuestions(collect.get(QuestionType.MULTIPLE_CHOICE), multipleNum, QuestionType.CN_MULTIPLE_CHOICE));
        associatedQuestions.addAll(getRandomQuestions(collect.get(QuestionType.TRUE_FALSE), judgmentNum, QuestionType.CN_TRUE_FALSE));
        List<Long> associatedId = new ArrayList<>();
        Integer allScore = 0;
        for (Question associatedQuestion : associatedQuestions) {
            allScore += associatedQuestion.getQuestionScore();
            associatedId.add(associatedQuestion.getQuestionId());
        }


        //生成试卷
        Long redisNum = redisTemplate.opsForValue().increment(RedisConstant.TEST_RANDOM_PREFIX, 1);
        String testName = "智能训练试卷-" + redisNum;
        RandomTest randomTest = new RandomTest();
        randomTest.setTestName(testName);
        randomTest.setSubjectId(subjectId);
        randomTest.setQuestionNum(associatedQuestions.size());
        randomTest.setTestScore(allScore);
        randomTest.setUserId(SecurityUtils.getUserId());
        int result = baseMapper.insert(randomTest);

        //试卷关联题目
        List<RandomTestQuestion> randomTestQuestions = associatedId.stream().map(id -> {
            RandomTestQuestion randomTestQuestion = new RandomTestQuestion();
            randomTestQuestion.setRandomTestId(randomTest.getRandomTestId());
            randomTestQuestion.setQuestionId(id);
            return randomTestQuestion;
        }).collect(Collectors.toList());
        randomTestQuestionService.saveBatch(randomTestQuestions);
        return result;
    }


    /**
     * 智能训练生成的试卷分页查询
     *
     * @param pageQuery 分页条件
     * @param userId    学生id
     * @return 随机试卷列表
     */
    @Override
    public Page<RandomTestVo> listRandomTests(PageQuery pageQuery, Long userId) {
        Page<RandomTestVo> page = PageHelper.startPage(pageQuery);
        baseMapper.listRandomTests(userId);
        return page;
    }


    private List<Question> getRandomQuestions(List<Question> allQuestions, Integer questionNum, String questionType) {
        if (questionNum == 0) {
            return Collections.emptyList();
        }
        //根本没有
        if (allQuestions == null) {
            throw new ServiceException(questionType + MessageConstant.QUESTION_TOO_LESS);
        }
        //有但是数量不够
        if (questionNum > allQuestions.size()) {
            throw new ServiceException(questionType + MessageConstant.QUESTION_TOO_LESS);
        }
        Random random = new Random();
        Set<Question> result = new HashSet<>();
        while (result.size() < questionNum) {
            //要加上最后一个
            int index = random.nextInt(allQuestions.size());
            result.add(allQuestions.get(index));
        }
        return new ArrayList<>(result);
    }
}




