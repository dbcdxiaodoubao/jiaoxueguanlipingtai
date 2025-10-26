package com.mashang.mapper;

import com.github.pagehelper.Page;
import com.mashang.domain.entity.TestAnswer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mashang.domain.query.student.TestPageQuery;
import com.mashang.domain.query.student.TestRecordQuery;
import com.mashang.domain.vo.student.TestListVo;
import com.mashang.domain.vo.student.TestRecordListVo;
import com.mashang.domain.vo.student.VideoTestVo;
import org.apache.ibatis.annotations.Param;

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
    /**
     *  查询学生未做完的答卷列表
     * @param userId 学生id
     * @return 学生未做完的试卷列表
     */
    List<TestListVo> getStudentTests(Long userId);


    /**
     * 根据提交时间倒叙分页查询该学生做过的试卷基本信息
     * @param testRecordQuery 查询条件
     * @return 学生做过的试卷基本信息
     */
    Page<TestRecordListVo> listTestRecord(TestRecordQuery testRecordQuery);
}




