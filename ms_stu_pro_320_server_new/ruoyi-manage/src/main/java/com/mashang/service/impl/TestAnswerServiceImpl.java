package com.mashang.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mashang.domain.entity.TestAnswer;
import com.mashang.service.ITestAnswerService;
import com.mashang.mapper.TestAnswerMapper;
import org.springframework.stereotype.Service;

/**
* @author 20413
* @description 针对表【ms_test_answer(答卷表)】的数据库操作Service实现
* @createDate 2025-10-22 18:01:40
*/
@Service
public class TestAnswerServiceImpl extends ServiceImpl<TestAnswerMapper, TestAnswer>
    implements ITestAnswerService {

}




