package com.mashang.service;

import com.mashang.domain.entity.Task;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mashang.domain.vo.student.TaskVo;
import com.ruoyi.common.core.domain.entity.SysUser;

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
    List<TaskVo> listStudentTasks(Long userId);
}
