package com.mashang.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mashang.domain.entity.QuestionAnswer;
import com.mashang.service.IQuestionAnswerService;
import com.mashang.mapper.QuestionAnswerMapper;
import org.springframework.stereotype.Service;

/**
* @author 20413
* @description 针对表【ms_question_answer(答题表)】的数据库操作Service实现
* @createDate 2025-10-22 18:01:40
*/
@Service
public class QuestionAnswerServiceImpl extends ServiceImpl<QuestionAnswerMapper, QuestionAnswer>
    implements IQuestionAnswerService {

}




