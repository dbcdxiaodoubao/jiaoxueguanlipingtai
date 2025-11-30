package com.mashang.service;

import com.mashang.domain.entity.Task;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mashang.domain.param.manage.TaskCreate;
import com.mashang.domain.vo.management.TaskDtlVo;
import com.mashang.domain.vo.student.TaskListVo;

import java.util.ArrayList;
import java.util.List;

/**
* @author 20413
* @description 针对表【ms_task(任务表)】的数据库操作Service
* @createDate 2025-10-22 18:01:40
*/
public interface ITaskService extends IService<Task> {
    /**
     * 查询当前学生所有学习任务列表
     * @param userId 学生ID
     * @return
     */
    List<TaskListVo> listStudentTasks(Long userId);

    /**
     * 新增任务
     * @param taskCreate
     * @return
     */
    boolean add(TaskCreate taskCreate);

    /**
     * 修改任务
     * @param task
     * @param testIds
     * @return
     */
    boolean update(Task task, ArrayList<Integer> testIds);

    /**
     * 任务详情
     * @param taskId
     * @return
     */
    TaskDtlVo detail(Integer taskId);
}
