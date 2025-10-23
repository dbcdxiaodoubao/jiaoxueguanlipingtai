package com.mashang.comming;

import com.mashang.domain.entity.QuestionAnswer;
import com.mashang.domain.query.student.QuestionSubmit;
import com.mashang.domain.vo.student.QuestionAnswerVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface QuestionAnswerMapping {

    QuestionAnswerMapping INSTANCE = Mappers.getMapper(QuestionAnswerMapping.class);

    List<QuestionAnswerVo> toQuestionAnswerVoList(List<QuestionAnswer> questionAnswers);

    List<QuestionAnswer> toQuestionAnswer(List<QuestionSubmit> questionSubmits);
}
