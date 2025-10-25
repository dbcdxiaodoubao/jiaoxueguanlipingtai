package com.mashang.comming;

import com.mashang.domain.query.student.RandomTestSubmit;
import com.mashang.domain.query.student.TestSubmit;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RandomTestMapping {
    RandomTestMapping INSTANCE = Mappers.getMapper(RandomTestMapping.class);

    TestSubmit toTestSubmit(RandomTestSubmit randomTestSubmit);
}
