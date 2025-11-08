package com.mashang.comming;

import com.mashang.domain.entity.RandomTest;
import com.mashang.domain.entity.Test;
import com.mashang.domain.entity.TestAnswer;
import com.mashang.domain.query.student.TestSubmit;
import com.mashang.domain.query.student.TestSubmitQuery;
import com.mashang.domain.vo.student.TestAnswerInfo;
import com.mashang.mapper.TestAnswerMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TestAnswerMapping {
    TestAnswerMapping INSTANCE = Mappers.getMapper(TestAnswerMapping.class);

    TestAnswerInfo toTestAnswerInfo(TestAnswer testAnswer);

    TestAnswer toTestAnswer(TestSubmit testSubmit);
     @Mapping(source = "testScore",target = "sumScore")
    TestAnswer toTestAnswer(RandomTest randomTest);

     TestSubmit toTestSubmit(TestSubmitQuery testSubmitQuery);

}
