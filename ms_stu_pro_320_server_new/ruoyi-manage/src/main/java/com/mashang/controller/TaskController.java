package com.mashang.controller;

import com.mashang.domain.vo.student.TaskVo;
import com.mashang.service.ITaskService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 有关任务的接口controller
 */

@RestController
@RequestMapping("/task")
@Api(tags = "任务管理")
public class TaskController extends BaseController {
    @Autowired
    private ITaskService taskService;


    @GetMapping("/student/list")
    @ApiOperation("查询当前学生所有学习任务列表")
    public R<List<TaskVo>> listStudentTasks(){
        Long userId = SecurityUtils.getUserId();
        List<TaskVo> taskVos = taskService.listStudentTasks(userId);
        return R.ok(taskVos);
    }
}
