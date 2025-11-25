package com.mashang.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mashang.comming.SubjectsMapping;
import com.mashang.constant.MessageConstant;
import com.mashang.domain.entity.Subjects;
import com.mashang.domain.vo.management.SubjectsListVo;
import com.mashang.domain.vo.student.SubjectsListByGradeVo;
import com.mashang.service.ISubjectsService;
import com.mashang.mapper.SubjectsMapper;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author 20413
 * @description 针对表【ms_subjects(学科表)】的数据库操作Service实现
 * @createDate 2025-10-22 18:01:40
 */
@Service
public class SubjectsServiceImpl extends ServiceImpl<SubjectsMapper, Subjects>
        implements ISubjectsService {

    @Autowired
    SubjectsMapper subjectsMapper;
    @Autowired
    private SysUserMapper sysUserMapper;


    @Override
    public List<SubjectsListVo> list(Long grade) {
        return subjectsMapper.list(grade);
    }

    @Override
    public List<SubjectsListByGradeVo> listByGrade() {
        Long userId = SecurityUtils.getUserId();
        SysUser user = sysUserMapper.selectUserById(userId);
        if (user == null) {
            throw new ServiceException(MessageConstant.STUDENT_NOT_EXIST);
        }
        Long grade = user.getGrade();
        LambdaQueryWrapper<Subjects> slqw = Wrappers.lambdaQuery();
        slqw.eq(Subjects::getGrade, grade);
        List<Subjects> subjects = subjectsMapper.selectList(slqw);
        List<SubjectsListByGradeVo> subjectsListByGradeVoList = SubjectsMapping.INSTANCE.toSubjectsListByGradeVoList(subjects);
        if (CollUtil.isEmpty(subjectsListByGradeVoList)) {
            return Collections.emptyList();
        }
        return subjectsListByGradeVoList;
    }

    @Override
    public Integer selectGradeById(Integer subjectId) {
        return subjectsMapper.selectGradeById(subjectId);
    }

    @Override
    public Integer selectBySubjectNameGrade(String subjectName, Integer grade) {
        return subjectsMapper.selectBySubjectNameGrade(subjectName, grade);
    }
}




