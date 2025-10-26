package com.mashang.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mashang.comming.QuestionMapping;
import com.mashang.domain.entity.Question;
import com.mashang.domain.query.management.QuestionCteat;
import com.mashang.domain.query.management.QuestionListQuery;
import com.mashang.domain.vo.management.QuestionDtlVo;
import com.mashang.domain.vo.management.QuestionListVo;
import com.mashang.service.IQuestionService;
import com.mashang.mapper.QuestionMapper;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.soap.Addressing;
import java.util.Collections;
import java.util.List;

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


}




