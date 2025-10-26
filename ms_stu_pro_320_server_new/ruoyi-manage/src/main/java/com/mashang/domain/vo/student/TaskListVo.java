package com.mashang.domain.vo.student;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Data
@Alias("studentTaskList")
public class TaskListVo {
    @ApiModelProperty(value = "任务id")
    private Integer taskId;

    @ApiModelProperty(value = "任务名称")
    private String taskName;

    @ApiModelProperty(value = "学生试卷列表")
    private List<TestAnswerListTaskVo> studentTests;
}
