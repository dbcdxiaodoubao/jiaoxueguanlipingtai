package com.mashang.service;

import com.github.pagehelper.Page;
import com.mashang.domain.entity.QuestionAnswer;
import com.mashang.domain.entity.TestAnswer;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mashang.domain.query.manage.TestAnswerPageQuery;
import com.mashang.domain.query.common.PageQuery;
import com.mashang.domain.query.student.TestRecordQuery;
import com.mashang.domain.query.student.TestSubmit;
import com.mashang.domain.vo.management.TestAnswerDtlVo;
import com.mashang.domain.vo.management.TestAnswerListVo;
import com.mashang.domain.vo.student.TestAnswerInfo;
import com.mashang.domain.vo.student.TestListVo;
import com.mashang.domain.vo.student.TestRecordListVo;
import com.mashang.domain.vo.student.VideoTestVo;
import com.ruoyi.common.core.page.TableDataInfo;

import java.util.List;

/**
* @author 20413
* @description 针对表【ms_test_answer(答卷表)】的数据库操作Service
* @createDate 2025-10-22 18:01:40
*/
public interface ITestAnswerService extends IService<TestAnswer> {
    /**
     *  查询学生未做完的答卷列表
     * @param userId 学生id
     * @return 学生未做完的试卷列表
     */
    List<TestListVo> getStudentTests(Long userId);

    /**
     * 根据答卷id查询答卷详情
     * @param id 答卷id
     * @return 答卷详情
     */
    TestAnswerInfo getStudentTestInfo(Long id);

    /**
     * 提交试卷
     * @param testSubmit 学生写完的试卷
     * @return 影响行数
     */
    Integer submitTest(TestSubmit testSubmit);

    /**
     * 查询未完成的视频试卷
     * @return 视频试卷列表
     */
    List<VideoTestVo> getVideoTests(Long userId);

    /**
     * 创建随机答卷
     * @param randomTestId 随机答卷id
     * @return 影响行数
     */
    Integer createRandomTest(Long randomTestId);

    /**
     * 查询随机试卷详情
     * @param randomTestId 随机试卷id
     * @return 随机试卷详情
     */
    TestAnswerInfo getRandomInfo(Long randomTestId);

    /**
     * 根据提交时间倒叙分页查询该学生做过的试卷基本信息
     * @param pageQuery 分页条件
     * @param testRecordQuery 查询条件
     * @return 学生做过的试卷基本信息
     */
    Page<TestRecordListVo> listTestRecord(PageQuery pageQuery, TestRecordQuery testRecordQuery);

    /**
     * 查询一共有多少答卷
     * @return
     */
    Long getTestAnswerCount();

    /**
     * 答卷分页查询
     * @param pageQuery 分页条件
     * @return 分页集合
     */
    TableDataInfo testAnswerlist(TestAnswerPageQuery pageQuery, Integer status);

    /**
     * 根据答卷id查询答卷详情
     * @param testAnswerId 答卷id
     * @return 答卷详情
     */
    TestAnswerDtlVo getTestAnswerInfo(Long testAnswerId);

    /**
     * 自动批改
     * @param testAnswerId 答卷id
     */
    void submitAutoCorrect(Long testAnswerId);

    /**
     * 手动批改主观题
     * @param questionAnswerList 批改信息
     */
    void correct(List<QuestionAnswer> questionAnswerList);
}
