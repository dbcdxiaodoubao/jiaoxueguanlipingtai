package com.mashang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mashang.domain.vo.management.StudentListVo;
import com.ruoyi.common.core.domain.entity.SysUser;

import java.util.List;


public interface IStudentService extends IService<SysUser> {

    /**
     * 查询学生信息列表
     * @param studentName
     * @return
     */
    List<StudentListVo> list(String studentName);
}
