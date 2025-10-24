package com.mashang.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mashang.domain.entity.Subjects;
import com.mashang.domain.vo.management.SubjectsListVo;
import com.mashang.service.ISubjectsService;
import com.mashang.mapper.SubjectsMapper;
import io.swagger.annotations.ApiModelProperty;
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

    @Override
    public List<SubjectsListVo> list(Long grade) {
        return subjectsMapper.list(grade);
    }
}




