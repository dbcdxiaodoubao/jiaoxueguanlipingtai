package com.mashang.mapper;

import com.mashang.domain.entity.Question;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

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
}




