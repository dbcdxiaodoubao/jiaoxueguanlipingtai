package com.mashang.service;

import com.mashang.domain.entity.Test;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mashang.domain.query.student.TestSubmit;
import com.mashang.domain.vo.student.TestAnswerInfo;
import com.mashang.domain.vo.student.TestListVo;

import java.util.List;

/**
* @author 20413
* @description 针对表【ms_test(试卷表)】的数据库操作Service
* @createDate 2025-10-22 18:01:40
*/
public interface ITestService extends IService<Test> {
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
}
