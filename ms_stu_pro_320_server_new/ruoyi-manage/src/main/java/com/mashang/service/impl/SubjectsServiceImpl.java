package com.mashang.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mashang.domain.entity.Subjects;
import com.mashang.service.ISubjectsService;
import com.mashang.mapper.SubjectsMapper;
import org.springframework.stereotype.Service;

/**
* @author 20413
* @description 针对表【ms_subjects(学科表)】的数据库操作Service实现
* @createDate 2025-10-22 18:01:40
*/
@Service
public class SubjectsServiceImpl extends ServiceImpl<SubjectsMapper, Subjects>
    implements ISubjectsService {

}




