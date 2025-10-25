package com.mashang.service;

import com.mashang.domain.entity.RandomTest;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mashang.domain.query.student.RandomTestQuery;

/**
* @author 20413
* @description 针对表【ms_random_test】的数据库操作Service
* @createDate 2025-10-25 11:54:19
*/
public interface IRandomTestService extends IService<RandomTest> {
    /**
     * 自动生成随机试卷
     * @param randomTestQuery 生成随机试卷的限制条件
     * @return 影响行数
     */
    Integer produceRandomTest(RandomTestQuery randomTestQuery);
}
