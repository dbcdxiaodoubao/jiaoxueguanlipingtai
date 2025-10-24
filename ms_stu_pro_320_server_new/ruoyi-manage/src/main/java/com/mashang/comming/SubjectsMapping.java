package com.mashang.comming;

import com.mashang.domain.entity.Subjects;
import com.mashang.domain.vo.management.SubjectsDtlVo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubjectsMapping {

    SubjectsMapping INSTANCE = Mappers.getMapper(SubjectsMapping.class);

    SubjectsDtlVo toDtlVo(Subjects subjects);
}
