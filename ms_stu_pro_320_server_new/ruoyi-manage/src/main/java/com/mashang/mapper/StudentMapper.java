package com.mashang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mashang.domain.vo.management.StudentDtlVo;
import com.mashang.domain.vo.management.StudentListVo;
import com.mashang.domain.vo.student.StudentInfoVo;
import com.ruoyi.common.core.domain.entity.SysUser;

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

    StudentInfoVo infoByid(Long userId);
}
