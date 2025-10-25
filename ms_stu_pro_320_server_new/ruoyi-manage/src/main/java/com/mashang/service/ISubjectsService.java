package com.mashang.service;

import com.mashang.domain.entity.Subjects;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mashang.domain.vo.management.SubjectsListVo;
import com.mashang.domain.vo.student.SubjectsListByGradeVo;

import java.util.List;

/**
* @author 20413
* @description 针对表【ms_subjects(学科表)】的数据库操作Service
* @createDate 2025-10-22 18:01:40
*/
public interface ISubjectsService extends IService<Subjects> {

    /**
     * 查询学科信息列表
     * @param grade
     * @return
     */
    List<SubjectsListVo> list(Long grade);

    /**
     * 通过学科名称和年级查询是否存在相同学科
     * @param subjectName
     * @param grade
     * @return
     */
    Integer selectBySubjectNameGrade(String subjectName, Integer grade);

    /**
     * 根据学生的年级查询学科列表
     * @return 学科列表
     */
    List<SubjectsListByGradeVo> listByGrade();

}
