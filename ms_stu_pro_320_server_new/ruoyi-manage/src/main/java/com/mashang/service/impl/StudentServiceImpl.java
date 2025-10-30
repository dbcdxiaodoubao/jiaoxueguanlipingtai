package com.mashang.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.mashang.comming.SubjectsMapping;
import com.mashang.domain.query.teacher.StudentPageQuery;
import com.mashang.domain.vo.management.StudentDtlVo;
import com.mashang.domain.vo.management.StudentListVo;
import com.mashang.domain.vo.student.LoginInfoVo;
import com.mashang.domain.vo.student.StudentInfoVo;
import com.mashang.domain.vo.teacher.TeacherClassListVo;
import com.mashang.mapper.ClassMapper;
import com.mashang.mapper.StudentMapper;
import com.mashang.service.IStudentService;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
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

    @Autowired
    private ClassMapper classMapper;


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
        return baseMapper.infoById(userId);
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
                .eq(SysLogininfor::getStatus, "0")//登录成功记录
                .select(SysLogininfor::getLoginTime)
                .list();
        //获取登录时间集合
        List<Date> loginTimeList = list.stream().map(SysLogininfor::getLoginTime).collect(Collectors.toList());
        return new LoginInfoVo(SecurityUtils.getUsername(), loginTimeList);
    }

    /**
     * 分页查询学生列表
     * @param query
     * @return
     */
    @Override
    public TableDataInfo pageQuery(StudentPageQuery query) {
        Page<SysUser> page = new Page<>(query.getPageNum(), query.getPageSize());
        List<Integer> classIds = classMapper.selectClassIds(SecurityUtils.getUserId());
        if(ObjectUtil.isEmpty(classIds))return new TableDataInfo();
        return new TableDataInfo(studentMapper.pageQuery(page, query.getUserName(),query.getGrade(),classIds), page.getTotal());
    }

    /**
     * 移除学生
     * @param userId
     */
    @Override
    public void exitClass(Integer userId) {
        lambdaUpdate().eq(SysUser::getUserId,userId).set(SysUser::getClassId,null).update();
    }


}
