package com.mashang.mapper;

import com.github.pagehelper.Page;
import com.mashang.domain.entity.Test;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mashang.domain.query.student.TestPageQuery;
import com.mashang.domain.query.student.VideoTestPageQuery;
import com.mashang.domain.vo.student.TestListVo;
import com.mashang.domain.vo.student.VideoTestVo;
import org.apache.ibatis.annotations.Param;

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
}




