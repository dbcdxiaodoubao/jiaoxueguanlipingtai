package com.mashang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mashang.domain.query.teacher.StudentPageQuery;
import com.mashang.domain.vo.management.StudentDtlVo;
import com.mashang.domain.vo.management.StudentListVo;
import com.mashang.domain.vo.student.LoginInfoVo;
import com.mashang.domain.vo.student.StudentInfoVo;
import com.mashang.domain.vo.teacher.TeacherClassListVo;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;

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

    /**
     * 分页查询学生列表
     * @param query
     * @return
     */
    TableDataInfo pageQuery(StudentPageQuery query);

    /**
     * 移除学生
     * @param userId
     */
    void exitClass(Integer userId);


}
