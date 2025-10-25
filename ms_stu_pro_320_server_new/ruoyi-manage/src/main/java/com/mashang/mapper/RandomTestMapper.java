package com.mashang.mapper;

import com.mashang.domain.entity.RandomTest;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mashang.domain.vo.student.RandomTestVo;

import java.util.List;

/**
* @author 20413
* @description 针对表【ms_random_test】的数据库操作Mapper
* @createDate 2025-10-25 11:54:19
* @Entity com.mashang.domain.entity.RandomTest
*/
public interface RandomTestMapper extends BaseMapper<RandomTest> {
    /**
     * 智能训练生成的试卷分页查询
     * @param userId 学生id
     * @return 随机试卷列表
     */
    List<RandomTestVo> listRandomTests(Long userId);
}




