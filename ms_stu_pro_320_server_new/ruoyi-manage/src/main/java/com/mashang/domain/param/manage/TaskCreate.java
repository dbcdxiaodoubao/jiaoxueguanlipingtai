package com.mashang.domain.param.manage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ApiModel("任务创建参数")
public class TaskCreate {

    @ApiModelProperty(value = "任务名称",required = true)
    @NotNull(message = "任务名称为空")
    private String taskName;

    @ApiModelProperty(value = "任务年级(1-12分别代表一年级到高三)",required = true)
    @NotNull(message = "任务年级为空")
    private Integer grade;

    @ApiModelProperty(value = "任务关联的试卷id",required = true)
    @NotNull(message = "任务关联的试卷id为空")
    List<Integer> testIds;
}
