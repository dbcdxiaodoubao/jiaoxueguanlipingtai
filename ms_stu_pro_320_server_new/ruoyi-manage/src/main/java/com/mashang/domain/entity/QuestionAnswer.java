package com.mashang.domain.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.mashang.domain.model.BaseModel;
import com.mashang.domain.model.Option;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 答题表
 * @TableName ms_question_answer
 */
@TableName(value ="ms_question_answer",autoResultMap = true)
@Data
@ApiModel(value="QuestionAnswer对象", description="答题表")
public class QuestionAnswer extends BaseModel {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "答题id")
    @TableId(value = "question_answer_id", type = IdType.AUTO)
    private Integer questionAnswerId;

    @ApiModelProperty(value = "学科id")
    private Integer subjectId;

    @ApiModelProperty(value = "答题人id")
    private Long userId;

    @ApiModelProperty(value = "答卷id")
    private Integer testAnswerId;

    @ApiModelProperty(value = "题目题干")
    private String questionTitle;

    @ApiModelProperty(value = "题目难度")
    private Integer questionDifficulty;

    @ApiModelProperty(value = "用户答案")
    private String userAnswer;

    @ApiModelProperty(value = "题目类型")
    private Integer questionType;

    @ApiModelProperty(value = "题目答案")
    @ExcelProperty("题目答案")
    private String rightAnswer;

    @ApiModelProperty(value = "题目分数")
    private Integer questionScore;

    @ApiModelProperty(value = "题目选项")
    @TableField(value = "`option`",typeHandler = JacksonTypeHandler.class)
    private Option option;

    @ApiModelProperty(value = "答题状态（0待批改 1正确 2错误）")
    private Integer status;

    @ApiModelProperty(value = "题目解析")
    @TableField("`explain`")
    private String explain;
}