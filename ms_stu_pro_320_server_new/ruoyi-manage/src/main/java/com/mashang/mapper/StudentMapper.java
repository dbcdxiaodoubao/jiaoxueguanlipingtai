package com.mashang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mashang.domain.query.teacher.StudentPageQuery;
import com.mashang.domain.vo.management.StudentDtlVo;
import com.mashang.domain.vo.management.StudentListVo;
import com.mashang.domain.vo.student.StudentInfoVo;
import com.ruoyi.common.core.domain.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper  extends BaseMapper<SysUser> {

    /**
     * 查询学生信息列表
     * @param studentName
     * @return
     */
    List<StudentListVo> list(String studentName);

    /**
     * 通过用户id查询学生详情
     * @param userId
     * @return
     */
    StudentDtlVo selectByid(Long userId);

    /**
     * 查询学生个人信息
     * @param userId
     * @return
     */
    StudentInfoVo infoById(Long userId);

    /**
     * 分页查询学生列表
     * @param page
     * @param userName
     * @param grade
     * @return
     */
    List<com.mashang.domain.vo.teacher.StudentListVo> pageQuery(@Param("page") Page<SysUser> page,
                                                                @Param("userName") String userName,
                                                                @Param("grade") Integer grade,
                                                                @Param("classIds") List<Integer> classIds);
}
