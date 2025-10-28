package com.mashang.mapper;

import com.mashang.domain.entity.Question;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mashang.domain.query.management.QuestionListQuery;
import com.mashang.domain.vo.management.MonthQuestion;
import com.mashang.domain.vo.management.QuestionDtlVo;
import com.mashang.domain.vo.management.QuestionListVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 20413
* @description 针对表【ms_question(题目表)】的数据库操作Mapper
* @createDate 2025-10-22 18:01:40
* @Entity com.mashang.domain.entity.Question
*/
public interface QuestionMapper extends BaseMapper<Question> {
    /**
     * 通过随机试卷id，查询卷下的题目
     * @param randomTestId 随机试卷id
     * @return 题目列表
     */
    List<Question> selectListByRandomTestId(Long randomTestId);

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

    /**
     * 问题和知识点关联
     * @param questionId
     * @param knowledgeId
     * @return
     */
    Integer linkQuestionKnowledge(@Param("questionId") Long questionId
            ,@Param("knowledgeId")  Integer knowledgeId);

    /**
     * 根据id删除和知识点的关联
     * @param questionId
     * @return
     */
    Integer deleteLink(Integer questionId);

    /**
     * 根据问题id查询是否与试卷关联
     * @param questionId
     * @return
     */
    Integer haveOnTest(Integer questionId);

    /**
     * 查询题目月数量
     * @return
     */
    List<MonthQuestion> monthQuestionList();

}




