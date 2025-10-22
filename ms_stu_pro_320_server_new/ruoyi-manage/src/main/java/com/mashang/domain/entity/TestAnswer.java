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
 * 答卷表
 * @TableName ms_test_answer
 */
@TableName(value ="ms_test_answer")
@Data
@ApiModel(value="TestAnswer对象", description="答卷表")
public class TestAnswer extends BaseModel {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "答卷id")
    @TableId(value = "test_answer_id", type = IdType.AUTO)
    private Integer testAnswerId;

    @ApiModelProperty(value = "试卷id")
    private Integer testId;

    @ApiModelProperty(value = "答卷人id")
    private Long userId;

    @ApiModelProperty(value = "答卷状态（0待批改 1已完成 2未完成）")
    private Integer status;

    @ApiModelProperty(value = "试卷总分")
    private Integer sumScore;

    @ApiModelProperty(value = "考试用时（秒）")
    private Integer duration;

    @ApiModelProperty(value = "试卷得分")
    private Integer userTestScore;
}