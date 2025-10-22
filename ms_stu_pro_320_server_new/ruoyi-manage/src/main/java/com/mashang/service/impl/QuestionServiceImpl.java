package com.mashang.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mashang.domain.entity.Question;
import com.mashang.service.IQuestionService;
import com.mashang.mapper.QuestionMapper;
import org.springframework.stereotype.Service;

/**
* @author 20413
* @description 针对表【ms_question(题目表)】的数据库操作Service实现
* @createDate 2025-10-22 18:01:40
*/
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question>
    implements IQuestionService {

}




