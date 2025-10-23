package com.mashang.mapper;

import com.mashang.domain.entity.Test;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mashang.domain.vo.student.TestListVo;

import java.util.List;

/**
* @author 20413
* @description 针对表【ms_test(试卷表)】的数据库操作Mapper
* @createDate 2025-10-22 18:01:40
* @Entity com.mashang.domain.entity.Test
*/
public interface TestMapper extends BaseMapper<Test> {
    /**
     *  查询学生未做完的答卷列表
     * @param userId 学生id
     * @return 学生未做完的试卷列表
     */
    List<TestListVo> getStudentTests(Long userId);
}




