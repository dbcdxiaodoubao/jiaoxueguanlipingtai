package com.mashang.util;

import com.mashang.domain.vo.student.SubjectsListByGradeVo;
import com.mashang.service.ISubjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class SubjectUtils {
    @Autowired
    private ISubjectsService subjectsService;

    public boolean checkSubject(Long subjectId){
        for (SubjectsListByGradeVo subjectsListByGradeVo : subjectsService.listByGrade()) {
            if (subjectsListByGradeVo.getSubjectId().equals(subjectId)) {
                return true;
            }
        }
        return false;
    }
}
