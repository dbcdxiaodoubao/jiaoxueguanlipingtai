package com.mashang.service;

import com.mashang.domain.entity.Question;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mashang.domain.query.management.QuestionListQuery;
import com.mashang.domain.vo.management.QuestionDtlVo;
import com.mashang.domain.vo.management.QuestionListVo;

import java.util.List;

/**
* @author 20413
* @description 针对表【ms_question(题目表)】的数据库操作Service
* @createDate 2025-10-22 18:01:40
*/
public interface IQuestionService extends IService<Question> {

    /**
     * 根据学科id查询是否存在题目
     * @param subjectId
     * @return
     */
    Integer haveQuestion(Integer subjectId);

    /**
     * 查询问题信息列表
     * @return
     */
    List<QuestionListVo> list(QuestionListQuery questionListQuery);

    /**
     * 根据id查询题目详情
     * @param questionId
     * @return
     */
    QuestionDtlVo dtl(Integer questionId);
}
