package com.mashang.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mashang.domain.entity.RandomTestQuestion;
import com.mashang.service.IRandomTestQuestionService;
import com.mashang.mapper.RandomTestQuestionMapper;
import org.springframework.stereotype.Service;

/**
* @author 20413
* @description 针对表【ms_random_test_question】的数据库操作Service实现
* @createDate 2025-10-25 11:54:19
*/
@Service
public class RandomTestQuestionServiceImpl extends ServiceImpl<RandomTestQuestionMapper, RandomTestQuestion>
    implements IRandomTestQuestionService {

}




