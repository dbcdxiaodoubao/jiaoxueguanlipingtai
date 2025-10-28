package com.mashang.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.mashang.domain.vo.management.StudentDtlVo;
import com.mashang.domain.vo.management.StudentListVo;
import com.mashang.domain.vo.student.LoginInfoVo;
import com.mashang.domain.vo.student.StudentInfoVo;
import com.mashang.mapper.StudentMapper;
import com.mashang.service.IStudentService;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.SysLogininfor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, SysUser>
        implements IStudentService {

    @Autowired
    StudentMapper studentMapper;

    @Override
    public List<StudentListVo> list(String studentName) {
        return studentMapper.list(studentName);
    }

    @Override
    public StudentDtlVo selectByid(Long userId) {
        return studentMapper.selectByid(userId);
    }

    /**
     * 查询学生个人信息
     * @param userId
     * @return
     */
    @Override
    public StudentInfoVo info(Long userId) {
        return baseMapper.infoByid(userId);
    }

    /**
     * 输入口令加入班级
     * @param classId
     */
    @Override
    public void joinClass(Integer classId) {
        lambdaUpdate().eq(SysUser::getUserId,SecurityUtils.getUserId())
                .set(SysUser::getClassId,classId)
                .update();
    }

    /**
     * 获取用户登录日志
     * @return
     */
    @Override
    public LoginInfoVo loginInfo() {
        List<SysLogininfor> list = Db.lambdaQuery(SysLogininfor.class)
                .eq(SysLogininfor::getUserName, SecurityUtils.getUsername())
                .select(SysLogininfor::getLoginTime)
                .list();
        List<Date> loginTimeList = list.stream().map(SysLogininfor::getLoginTime).collect(Collectors.toList());
        return new LoginInfoVo(SecurityUtils.getUsername(), loginTimeList);
    }
}
