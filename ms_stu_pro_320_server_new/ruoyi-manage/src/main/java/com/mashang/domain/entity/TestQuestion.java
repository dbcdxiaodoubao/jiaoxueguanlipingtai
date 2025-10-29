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
 * 试卷-题目表
 * @TableName ms_test_question
 */
@TableName(value ="ms_test_question")
@Data
@ApiModel(value="TestQuestion对象", description="试卷-题目表")
public class TestQuestion extends BaseModel {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "试卷-题目id")
    @TableId(value = "test_question_id", type = IdType.AUTO)
    private Integer testQuestionId;

    @ApiModelProperty(value = "题目id")
    private Long questionId;

    @ApiModelProperty(value = "试卷id")
    private Integer testId;

    @ApiModelProperty(value = "大标题")
    private String bigTitle;

    @ApiModelProperty(value = "顺序")
    private Integer order;
}