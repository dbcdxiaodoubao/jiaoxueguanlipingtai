package com.mashang.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mashang.domain.vo.management.StudentListVo;
import com.mashang.mapper.StudentMapper;
import com.mashang.service.IStudentService;
import com.ruoyi.common.core.domain.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, SysUser>
        implements IStudentService {

    @Autowired
    StudentMapper studentMapper;

    @Override
    public List<StudentListVo> list(String studentName) {
        return studentMapper.list(studentName);
    }
}
