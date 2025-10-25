package com.mashang.comming;

import com.mashang.domain.entity.Subjects;
import com.mashang.domain.query.management.SubjectsCreat;
import com.mashang.domain.query.management.SubjectsUpdate;
import com.mashang.domain.vo.management.SubjectsDtlVo;
import com.mashang.domain.vo.student.SubjectsListByGradeVo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectsMapping {

    SubjectsMapping INSTANCE = Mappers.getMapper(SubjectsMapping.class);

    SubjectsDtlVo toDtlVo(Subjects subjects);

    Subjects toCreate(SubjectsCreat subjectsCreat);

    List<SubjectsListByGradeVo> toSubjectsListByGradeVoList(List<Subjects> subjects);

    Subjects toUpdate(SubjectsUpdate subjectsUpdate);
}
