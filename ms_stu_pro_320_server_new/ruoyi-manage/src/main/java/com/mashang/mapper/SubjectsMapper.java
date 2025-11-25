package com.mashang.mapper;

import com.mashang.domain.entity.Subjects;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mashang.domain.vo.management.SubjectsDtlVo;
import com.mashang.domain.vo.management.SubjectsListVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 20413
* @description 针对表【ms_subjects(学科表)】的数据库操作Mapper
* @createDate 2025-10-22 18:01:40
* @Entity com.mashang.domain.entity.Subjects
*/
public interface SubjectsMapper extends BaseMapper<Subjects> {

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
    Integer selectBySubjectNameGrade(@Param("subjectName")String subjectName
            ,@Param("grade") Integer grade);

    /**
     * 通过学科id查年级
     * @param subjectId
     * @return
     */
    Integer selectGradeById(Integer subjectId);

    /**
     * 通过学科id查年级
     * @param subjectName
     * @return
     */
    Integer selectGradeByIName(String subjectName);
}




