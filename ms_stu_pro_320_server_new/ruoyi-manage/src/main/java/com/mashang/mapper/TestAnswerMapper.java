package com.mashang.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mashang.domain.entity.TestAnswer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mashang.domain.vo.management.TestAnswerDtlVo;
import com.mashang.domain.vo.management.TestAnswerListVo;
import com.mashang.domain.query.student.TestRecordQuery;
import com.mashang.domain.vo.student.TestListVo;
import com.mashang.domain.vo.student.TestRecordListVo;
import com.mashang.domain.vo.student.VideoTestVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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


    List<TestAnswerListVo> testAnswerList(Page<TestAnswer> page, @Param("subjectId") Integer subjectId,
                                          @Param("userIds") List<Long> userIds, @Param("status") Integer status );


    /**
     * 根据提交时间倒叙分页查询该学生做过的试卷基本信息
     * @param testRecordQuery 查询条件
     * @return 学生做过的试卷基本信息
     */
    Page<TestRecordListVo> listTestRecord(TestRecordQuery testRecordQuery);

    /**
     * 查询一共有多少答卷
     * @return
     */
    Long getTestAnswerCount();

    /**
     * 根据答卷id查询答卷详情
     * @param testAnswerId 答卷id
     * @return 答卷详情
     */
    TestAnswerDtlVo getTestAnswerInfo(Long testAnswerId);

    /**
     * 根据用户id查询答卷id
     * @param userIds 用户id
     * @return 答卷id
     */
    List<Integer> selectIdsByUserIds(@Param("userIds") List<Integer> userIds);

    /**
     * 根据答卷id查询总分
     * @param testAnswerIds 答卷id
     * @return 总分
     */
    Integer getSumScore(@Param("testAnswerIds") List<Integer> testAnswerIds);

    /**
     * 根据用户id查询答卷id集合
     * @param userId
     * @return
     */
    @Select("select test_answer_id from ms_test_answer where user_id = #{userId}")
    List<Integer> selectIdsByUserId(Integer userId);
}




