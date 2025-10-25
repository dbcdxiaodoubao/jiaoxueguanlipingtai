package com.mashang.domain.vo.management;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class TaskDtlVo {

    @ApiModelProperty(value = "任务年级(1-12分别代表一年级到高三)")
    private Integer grade;

    @ApiModelProperty(value = "任务名称")
    private String taskName;

    @ApiModelProperty(value = "任务内容试卷")
    private List<TaskTestVo> taskTests;
}
