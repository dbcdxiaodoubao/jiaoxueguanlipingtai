package com.mashang.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mashang.domain.entity.TestQuestion;
import com.mashang.service.ITestQuestionService;
import com.mashang.mapper.TestQuestionMapper;
import org.springframework.stereotype.Service;

/**
* @author 20413
* @description 针对表【ms_test_question(试卷-题目表)】的数据库操作Service实现
* @createDate 2025-10-22 18:01:40
*/
@Service
public class TestQuestionServiceImpl extends ServiceImpl<TestQuestionMapper, TestQuestion>
    implements ITestQuestionService {

}




