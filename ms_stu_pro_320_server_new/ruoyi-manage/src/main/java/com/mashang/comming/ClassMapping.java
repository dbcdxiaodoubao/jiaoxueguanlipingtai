package com.mashang.comming;

import com.mashang.domain.entity.Class;
import com.mashang.domain.vo.teacher.ClassListVo;
import com.mashang.domain.vo.teacher.TeacherClassListVo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClassMapping {
    List<TeacherClassListVo> toTeacherClassListVo(List<Class> list);

    List<ClassListVo> toClassListVo(List<Class> records);
}
