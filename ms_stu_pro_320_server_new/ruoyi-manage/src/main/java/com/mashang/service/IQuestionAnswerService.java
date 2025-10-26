package com.mashang.service;

import com.github.pagehelper.Page;
import com.mashang.domain.entity.QuestionAnswer;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mashang.domain.query.common.PageQuery;
import com.mashang.domain.query.student.WrongBookQuery;
import com.mashang.domain.vo.student.WrongBookListVo;

/**
* @author 20413
* @description 针对表【ms_question_answer(答题表)】的数据库操作Service
* @createDate 2025-10-22 18:01:40
*/
public interface IQuestionAnswerService extends IService<QuestionAnswer> {
    /**
     *  查询用户错题列表
     * @param pageQuery 分页条件
     * @param wrongBookQuery 查询条件
     * @return 错题列表
     */
    Page<WrongBookListVo> pageWrongBook(PageQuery pageQuery, WrongBookQuery wrongBookQuery);
}
