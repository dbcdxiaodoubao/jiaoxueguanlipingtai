package com.mashang.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mashang.comming.TaskMapping;
import com.mashang.domain.entity.Task;
import com.mashang.domain.entity.TaskTest;
import com.mashang.domain.param.manage.TaskCreate;
import com.mashang.domain.param.manage.TaskUpdate;
import com.mashang.domain.query.manage.TaskPageQuery;
import com.mashang.domain.vo.management.TaskDtlVo;
import com.mashang.service.ITaskService;
import com.mashang.service.ITaskTestService;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
@Api(tags = "任务管理")
public class TaskManageController {

    private final ITaskService taskService;
    private final TaskMapping taskMapping;
    private final ITaskTestService taskTestService;

    @GetMapping("/list")
    @ApiOperation("任务列表")
    @PreAuthorize("@ss.hasPermi('teacher:task:list')")
    public TableDataInfo list(@Validated TaskPageQuery query) {
        Page<Task> page=new Page<>(query.getPageNum(),query.getPageSize());
        taskService.page(page,new LambdaQueryWrapper<Task>()
                .eq(ObjectUtil.isNotNull(query.getGrade()),Task::getGrade,query.getGrade())
                .orderByDesc(Task::getCreateTime));
        return new TableDataInfo(taskMapping.toTaskListVoList(page.getRecords()),page.getTotal());
    }

    @PostMapping
    @ApiOperation("创建任务")
    @PreAuthorize("@ss.hasPermi('teacher:task:insert')")
    public R<Void> add(@RequestBody @Validated TaskCreate taskCreate) {
        // 判断任务名称是否存在
        if(taskService.lambdaQuery()
                .eq(Task::getTaskName, taskCreate.getTaskName())
                .eq(Task::getGrade, taskCreate.getGrade())
                .exists()
        )return R.fail("任务名称已存在");
        return R.result(taskService.add(taskCreate));
    }

    @PutMapping
    @ApiOperation("修改任务")
    @PreAuthorize("@ss.hasPermi('teacher:task:update')")
    public R<Void> update(@RequestBody @Validated TaskUpdate taskUpdate) {
        // 判断任务名称是否存在
        if(taskService.lambdaQuery()
                .eq(Task::getTaskName,taskUpdate.getTaskName())
                .eq(Task::getGrade,taskUpdate.getGrade())
                .ne(Task::getTaskId,taskUpdate.getTaskId())
                .exists()
        )return R.fail("任务名称已存在");
        return R.result(taskService.updateById(taskMapping.toPo(taskUpdate)));
    }

    @DeleteMapping("/{taskId}")
    @ApiOperation("删除任务")
    @PreAuthorize("@ss.hasPermi('teacher:task:delete')")
    public R<Void> delete(@ApiParam("任务id")
                              @NotNull(message = "任务id为空")
                              @PathVariable Integer taskId) {
        if (taskTestService.lambdaQuery()
                .eq(TaskTest::getTaskId,taskId)
                .exists()
        )return R.fail("此任务下有关联试卷");
        return R.result(taskService.removeById(taskId));
    }

    @GetMapping("/{taskId}")
    @ApiOperation("查询任务详情")
    @PreAuthorize("@ss.hasPermi('teacher:task:dtl')")
    public R<TaskDtlVo> detail(@ApiParam("任务id")
                                   @NotNull(message = "任务id为空")
                                   @PathVariable Integer taskId){
        return R.ok(taskService.detail(taskId));
    }


}
