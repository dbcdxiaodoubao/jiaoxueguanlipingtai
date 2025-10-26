package com.mashang.comming;

import com.mashang.domain.entity.Question;
import com.mashang.domain.query.management.QuestionCteat;
import com.mashang.domain.query.management.QuestionExcelCteat;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface QuestionMapping {

    QuestionMapping INSTANCE = Mappers.getMapper(QuestionMapping.class);

    Question toCreat(QuestionCteat questionCteat);

    List<Question> toList(List<QuestionExcelCteat> questionExcelCteats);

}
