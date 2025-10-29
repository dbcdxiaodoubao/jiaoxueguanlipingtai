package com.mashang.mapper;

import com.mashang.domain.entity.Task;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mashang.domain.vo.management.TaskDtlVo;
import com.mashang.domain.vo.student.TaskListVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 20413
* @description 针对表【ms_task(任务表)】的数据库操作Mapper
* @createDate 2025-10-22 18:01:40
* @Entity com.mashang.domain.entity.Task
*/
public interface TaskMapper extends BaseMapper<Task> {
    /**
     * 查询当前学生所有学习任务列表
     * @param grade 年级
     * @param userId 学生id
     * @return 学生任务列表（包含答卷名，任务名）
     */
    List<TaskListVo> listStudentTasks(@Param("grade") Long grade, @Param("userId") Long userId);

    /**
     * 获取任务详情
     * @param taskId 任务id
     * @return 任务详情
     */
    TaskDtlVo detail(Integer taskId);
}




