package com.mashang.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

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
public class TaskTestAnswer extends BaseModel {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "答卷-任务id")
    @TableId(value = "task_test_id", type = IdType.AUTO)
    private Integer taskTestAnswerId;

    @ApiModelProperty(value = "任务id")
    private Integer taskAnswerId;

    @ApiModelProperty(value = "答卷id")
    private Integer testId;
}