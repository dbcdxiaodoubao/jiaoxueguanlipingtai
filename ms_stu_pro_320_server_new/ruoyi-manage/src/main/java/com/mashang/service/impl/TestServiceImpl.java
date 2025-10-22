package com.mashang.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mashang.domain.entity.Test;
import com.mashang.service.ITestService;
import com.mashang.mapper.TestMapper;
import org.springframework.stereotype.Service;

/**
* @author 20413
* @description 针对表【ms_test(试卷表)】的数据库操作Service实现
* @createDate 2025-10-22 18:01:40
*/
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test>
    implements ITestService {

}




