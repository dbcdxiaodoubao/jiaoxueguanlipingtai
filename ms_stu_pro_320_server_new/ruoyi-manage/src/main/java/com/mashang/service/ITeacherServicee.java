package com.mashang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mashang.domain.vo.management.TeacherDtlVo;
import com.mashang.domain.vo.management.TeacherListVo;
import com.mashang.domain.vo.teacher.*;
import com.ruoyi.common.core.domain.entity.SysUser;

import java.util.List;
import java.util.Map;

public interface ITeacherServicee extends IService<SysUser> {

    /**
     * 根据老师姓名模糊查询教师信息
     * @param nickName
     * @return
     */
    List<TeacherListVo> list(String nickName);

    /**
     * 通过id查询教师详情
     * @param id
     * @return
     */
    TeacherDtlVo selectByid(Long id);

    /**
     * 查询班级总数，班级总人数，试卷总数，题目总数
     * @return
     */
    TotalVo total();

    /**
     * 查询班级人数分布
     * @return
     */
    List<ClassSizeDistributionVo> classSizeDistribution();

    /**
     * 查询班级试卷分布
     * @return
     */
    List<ClassTestDistributionVo> classTestDistribution();

    /**
     * 查询班级平均分
     * @return
     */
    List<TestAverageVo> testAverage();

    /**
     * 查询班级下学生的成绩
     * @param classId
     * @return
     */
    List<StudentAverageVo> studentAverage(Integer classId);
}
