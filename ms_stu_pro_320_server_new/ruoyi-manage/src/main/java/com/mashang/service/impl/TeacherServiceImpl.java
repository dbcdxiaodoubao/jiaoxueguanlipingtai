package com.mashang.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mashang.domain.vo.management.TeacherDtlVo;
import com.mashang.domain.vo.management.TeacherListVo;
import com.mashang.mapper.TeacherMapper;
import com.mashang.service.ITeacherServicee;
import com.ruoyi.common.core.domain.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, SysUser>
        implements ITeacherServicee {

    @Autowired
    TeacherMapper teacherMapper;

    @Override
    public List<TeacherListVo> list(String nickName) {
        return teacherMapper.list(nickName);
    }

    @Override
    public TeacherDtlVo selectByid(Long id) {
        return teacherMapper.selectByid(id);
    }
}
