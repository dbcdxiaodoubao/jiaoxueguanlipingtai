package com.mashang.domain.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 任务-试卷表
 * </p>
 *
 * @author 离子
 * @since 2025-10-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ms_task_test")
@ApiModel(value="TaskTest对象", description="任务-试卷表")
public class TaskTest implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "任务-试卷id")
    @TableId(value = "task_test_id", type = IdType.AUTO)
    private Integer taskTestId;

    @ApiModelProperty(value = "任务id")
    private Integer taskId;

    @ApiModelProperty(value = "试卷id")
    private Integer testId;

    @ApiModelProperty(value = "创建人")
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新人")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "逻辑删除（0正常 2删除）")
    private String delFlag;

    @ApiModelProperty(value = "备注")
    private String remark;


}
