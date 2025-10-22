package com.mashang.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mashang.domain.entity.TestClass;
import com.mashang.service.ITestClassService;
import com.mashang.mapper.TestClassMapper;
import org.springframework.stereotype.Service;

/**
* @author 20413
* @description 针对表【ms_test_class(试卷-班级表)】的数据库操作Service实现
* @createDate 2025-10-22 18:01:40
*/
@Service
public class TestClassServiceImpl extends ServiceImpl<TestClassMapper, TestClass>
    implements ITestClassService {

}




