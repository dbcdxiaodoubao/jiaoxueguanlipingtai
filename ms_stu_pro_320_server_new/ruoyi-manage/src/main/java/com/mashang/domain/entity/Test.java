package com.mashang.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mashang.domain.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 试卷表
 * @TableName ms_test
 */
@TableName(value ="ms_test")
@Data
@ApiModel(value="Test对象", description="试卷表")
public class Test extends BaseModel {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "试卷主键")
    @TableId(value = "test_id", type = IdType.AUTO)
    private Integer testId;

    @ApiModelProperty(value = "试卷名称")
    private String testName;

    @ApiModelProperty(value = "学科id")
    private Integer subjectId;

    @ApiModelProperty(value = "试卷类型")
    private Integer testType;

    @ApiModelProperty(value = "试卷发布时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startTime;

    @ApiModelProperty(value = "截止时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date deadline;

    @ApiModelProperty(value = "试卷总分")
    private Integer testScore;

    @ApiModelProperty(value = "建议考试时长（分钟）")
    private Integer suggestDuration;

    @ApiModelProperty(value = "题目总数")
    private Integer questionNum;
}