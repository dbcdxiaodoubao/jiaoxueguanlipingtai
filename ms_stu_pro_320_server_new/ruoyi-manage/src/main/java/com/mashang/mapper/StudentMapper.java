package com.mashang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mashang.domain.vo.management.StudentListVo;
import com.ruoyi.common.core.domain.entity.SysUser;

import java.util.List;

public interface StudentMapper  extends BaseMapper<SysUser> {

    /**
     * 查询学生信息列表
     * @param studentName
     * @return
     */
    List<StudentListVo> list(String studentName);
}
