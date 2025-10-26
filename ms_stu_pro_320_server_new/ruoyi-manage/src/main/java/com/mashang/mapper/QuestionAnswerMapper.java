package com.mashang.mapper;

import com.github.pagehelper.Page;
import com.mashang.domain.entity.QuestionAnswer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mashang.domain.query.student.WrongBookQuery;
import com.mashang.domain.vo.student.WrongBookListVo;

/**
* @author 20413
* @description 针对表【ms_question_answer(答题表)】的数据库操作Mapper
* @createDate 2025-10-22 18:01:40
* @Entity com.mashang.domain.entity.QuestionAnswer
*/
public interface QuestionAnswerMapper extends BaseMapper<QuestionAnswer> {
    /**
     *  查询用户错题列表
     * @param wrongBookQuery 查询条件
     * @return 错题列表
     */
    Page<WrongBookListVo> pageWrongBook(WrongBookQuery wrongBookQuery);
}




