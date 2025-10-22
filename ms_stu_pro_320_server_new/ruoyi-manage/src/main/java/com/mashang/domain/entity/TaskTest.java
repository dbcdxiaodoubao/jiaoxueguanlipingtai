package com.mashang.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

import com.mashang.domain.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 任务-试卷表
 * @TableName ms_task_test
 */
@TableName(value ="ms_task_test")
@Data
@ApiModel(value="TaskTest对象", description="任务-试卷表")
public class TaskTest extends BaseModel {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "试卷-任务id")
    @TableId(value = "task_test_id", type = IdType.AUTO)
    private Integer taskTestId;

    @ApiModelProperty(value = "任务id")
    private Integer taskId;

    @ApiModelProperty(value = "试卷id")
    private Integer testId;
}