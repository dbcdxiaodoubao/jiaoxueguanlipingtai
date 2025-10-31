package com.mashang.comming;

import com.mashang.domain.entity.Test;
import com.mashang.domain.query.management.TestCreat;
import com.mashang.domain.query.management.TestUpdate;
import com.mashang.domain.vo.teacher.TestDtlVo;
import com.mashang.domain.vo.teacher.TestListVo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TestMapping {

    TestMapping INSTANCE = Mappers.getMapper(TestMapping.class);

    Test toCreat(TestCreat testCreat);

    Test toUpdate(TestUpdate testUpdate);

    TestDtlVo toTestDtlVo(Test test);
}
