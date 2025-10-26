package com.mashang.service;

import com.github.pagehelper.Page;
import com.mashang.domain.entity.Test;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mashang.domain.query.common.PageQuery;
import com.mashang.domain.query.student.RandomTestQuery;
import com.mashang.domain.query.student.TestPageQuery;
import com.mashang.domain.query.student.TestSubmit;
import com.mashang.domain.query.student.VideoTestPageQuery;
import com.mashang.domain.vo.student.TestAnswerInfo;
import com.mashang.domain.vo.student.TestListVo;
import com.mashang.domain.vo.student.VideoTestVo;

import java.util.List;

/**
* @author 20413
* @description 针对表【ms_test(试卷表)】的数据库操作Service
* @createDate 2025-10-22 18:01:40
*/
public interface ITestService extends IService<Test> {

    /**
     * 查询学生所有的答卷列表
     * @param pageQuery 分页条件
     * @param testPageQuery 试卷的分页条件 类型和学科
     * @return 学生所有的试卷列表
     */
    Page<TestListVo> pageStudentTests(PageQuery pageQuery, TestPageQuery testPageQuery);

    /**
     *  查询学生所有视频答卷列表
     * @param pageQuery 分页参数
     * @param videoTestPageQuery 视频查询条件
     * @return 学生所有视频答卷列表
     */
    Page<VideoTestVo> pageVideoTests(PageQuery pageQuery, VideoTestPageQuery videoTestPageQuery);

    /**
     * 根据学科id查有没有试卷
     * @param subjectId
     * @return
     */
    Integer haveTest(Integer subjectId);
}
