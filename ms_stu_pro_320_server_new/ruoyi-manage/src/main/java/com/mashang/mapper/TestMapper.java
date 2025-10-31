package com.mashang.mapper;

import com.github.pagehelper.Page;
import com.mashang.domain.entity.Test;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mashang.domain.query.management.QuestionTestCreat;
import com.mashang.domain.query.management.TestListQuery;
import com.mashang.domain.query.student.TestPageQuery;
import com.mashang.domain.query.student.VideoTestPageQuery;
import com.mashang.domain.vo.management.ManageTestListVo;
import com.mashang.domain.vo.management.TestDtlVo;
import com.mashang.domain.vo.student.TestListVo;
import com.mashang.domain.vo.student.VideoTestVo;
import org.apache.ibatis.annotations.Param;

import java.beans.Introspector;
import java.util.List;

/**
* @author 20413
* @description 针对表【ms_test(试卷表)】的数据库操作Mapper
* @createDate 2025-10-22 18:01:40
* @Entity com.mashang.domain.entity.Test
*/
public interface TestMapper extends BaseMapper<Test> {


    /**
     * 查询学生所有的答卷列表
     * @param testPageQuery 试卷的分页条件 类型和学科
     * @return 学生所有的试卷列表
     */
    Page<TestListVo> pageStudentTests(@Param("t") TestPageQuery testPageQuery);
    /**
     *  查询学生所有视频答卷列表
     * @param videoTestPageQuery 视频查询条件
     * @return 学生所有视频答卷列表
     */
    Page<VideoTestVo> pageVideoTests(@Param("v") VideoTestPageQuery videoTestPageQuery);

    /**
     * 根据学科id查有没有试卷
     * @param subjectId
     * @return
     */
    Integer haveTest(Integer subjectId);

    /**
     * 管理端查询试卷列表
     * @param testListQuery
     * @return
     */
    List<ManageTestListVo> list(TestListQuery testListQuery);

    /**
     * 根据试卷id查询试卷详情
     * @param testId
     * @return
     */
    TestDtlVo dtl(Integer testId);

    /**
     * 根据试卷id绑定问题
     * @param testId
     * @param questionTestCreat
     * @return
     */
    Integer linkTestQuestion(@Param("testId") Integer testId
            ,@Param("questionTestCreat") QuestionTestCreat questionTestCreat);

    /**
     * 根据试卷id删除题目关联
     * @param testId
     * @return
     */
    Integer breakTestQuestion(Integer testId);

    /**
     * 根据试卷id查询是否存在答卷
     * @param testId
     * @return
     */
    Integer haveTestAnswer(Integer testId);

    List<com.mashang.domain.vo.teacher.TestListVo> pageQueryTeacher(@Param("page") Page<Test> page,
                                                                    @Param("query") com.mashang.domain.query.teacher.TestPageQuery query,
                                                                    @Param("grade") Long grade);
}




