package com.mashang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mashang.domain.vo.management.StudentDtlVo;
import com.mashang.domain.vo.management.StudentListVo;
import com.mashang.domain.vo.student.LoginInfoVo;
import com.mashang.domain.vo.student.StudentInfoVo;
import com.ruoyi.common.core.domain.entity.SysUser;

import java.util.List;


public interface IStudentService extends IService<SysUser> {

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
     * 查询个人信息
     * @param userId
     * @return
     */
    StudentInfoVo info(Long userId);

    /**
     * 输入口令加入班级
     * @param classId
     */
    void joinClass(Integer classId);

    /**
     * 获取用户登录日志
     * @return
     */
    LoginInfoVo loginInfo();

}
