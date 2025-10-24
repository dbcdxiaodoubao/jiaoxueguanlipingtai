package com.mashang.mapper;

import com.mashang.domain.entity.TestAnswer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mashang.domain.vo.student.VideoTestVo;

import java.util.List;

/**
* @author 20413
* @description 针对表【ms_test_answer(答卷表)】的数据库操作Mapper
* @createDate 2025-10-22 18:01:40
* @Entity com.mashang.domain.entity.TestAnswer
*/
public interface TestAnswerMapper extends BaseMapper<TestAnswer> {
    /**
     * 查询未完成的视频试卷
     * @param userId 学生id
     * @return 视频试卷列表
     */
    List<VideoTestVo> getVideoTests(Long userId);
}




