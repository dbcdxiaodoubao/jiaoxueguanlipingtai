package com.mashang.service.impl;

import cn.hutool.core.lang.TypeReference;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashang.comming.QuestionMapping;
import com.mashang.domain.entity.Question;
import com.mashang.domain.model.Option;
import com.mashang.domain.query.management.QuestionCteat;
import com.mashang.domain.query.management.QuestionListQuery;
import com.mashang.domain.vo.management.MonthQuestion;
import com.mashang.domain.vo.management.QuestionDtlVo;
import com.mashang.domain.vo.management.QuestionListVo;
import com.mashang.service.IQuestionService;
import com.mashang.mapper.QuestionMapper;
import com.ruoyi.common.exception.ServiceException;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.xml.ws.soap.Addressing;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
* @author 20413
* @description 针对表【ms_question(题目表)】的数据库操作Service实现
* @createDate 2025-10-22 18:01:40
*/
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question>
    implements IQuestionService {

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    private ObjectMapper objectMapper;


    @Override
    public Integer haveQuestion(Integer subjectId) {
        return questionMapper.haveQuestion(subjectId);
    }

    @Override
    public List<QuestionListVo> list(QuestionListQuery questionListQuery) {
        return questionMapper.list(questionListQuery);
    }

    @Override
    public QuestionDtlVo dtl(Integer questionId) {
        return questionMapper.dtl(questionId);
    }

    @Override
    public boolean insert(QuestionCteat questionCteat) {

        Question question = QuestionMapping.INSTANCE.toCreat(questionCteat);

        if(questionMapper.insert(question) == 0){
            return false;
        }

        Long questionId = question.getQuestionId();

        for(Integer knowledgeId :  questionCteat.getKnowledgeId()){
            questionMapper.linkQuestionKnowledge(questionId, knowledgeId);
        }

        return true;
    }

    @Override
    public Integer linkQuestionKnowledge(Long questionId, Integer knowledgeId) {
        return questionMapper.linkQuestionKnowledge(questionId, knowledgeId);
    }

    @Override
    public Integer deleteLink(Integer questionId) {
        return questionMapper.deleteLink(questionId);
    }

    @Override
    public Integer haveOnTest(Integer questionId) {
        return questionMapper.haveOnTest(questionId);
    }

    @Override
    public List<MonthQuestion> monthQuestionList() {
        return questionMapper.monthQuestionList();
    }

    @Override
    public Integer saveQuestion(QuestionCteat questionCteat) {

        Integer type = questionCteat.getQuestionType();

        List<Option> list = questionCteat.getOption();

        if(type<0 || type>4){
            throw new ServiceException("题目类型非法，应为0-4", 500);
        }
        if(questionCteat.getQuestionDifficulty()<1 || questionCteat.getQuestionDifficulty()>10){
            throw new ServiceException("题目难度非法，应为1-10", 500);
        }
        if(questionCteat.getQuestionScore()<=0){
            throw new ServiceException("题目分数应大于0", 500);
        }
        if (type == 0 && list.size()!=4) {
            throw new ServiceException("单选题的选项应为4个", 500);
        }
        if (type == 2 && list.size()!=2) {
            throw new ServiceException("判断题选项应为2个", 500);
        }
        if(type == 3 || type == 4){
            questionCteat.setOption(null);
        }

       return questionMapper.insert(QuestionMapping.INSTANCE.toCreat(questionCteat));
    }


}




