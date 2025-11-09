package com.mashang.domain.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.mashang.domain.model.BaseModel;
import com.mashang.domain.model.Option;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 题目表
 * @TableName ms_question
 */
@TableName(value ="ms_question",autoResultMap = true)
@Data
@ApiModel(value="Question对象", description="题目表")
public class Question extends BaseModel {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("题目id")
    @TableId(value = "question_id", type = IdType.AUTO)
    private Long questionId;

    @ApiModelProperty(value = "学科id")
    @ExcelProperty("学科id")
    private Integer subjectId;

    @ApiModelProperty(value = "题目分数")
    @ExcelProperty("题目分数")
    private Integer questionScore;

    @ApiModelProperty(value = "题目难度（1-10）")
    @ExcelProperty("题目难度（1-10）")
    private Integer questionDifficulty;

    @ApiModelProperty(value = "题目类型")
    @ExcelProperty("题目类型")
    private Integer questionType;

    @ApiModelProperty(value = "题目题干")
    @ExcelProperty("题目题干")
    private String questionTitle;

    @ApiModelProperty(value = "题目答案")
    @ExcelProperty("题目答案")
    private String questionAnswer;

    @ApiModelProperty(value = "题目选项")
    @TableField(value = "`option`",typeHandler = JacksonTypeHandler.class)
    @ExcelProperty("题目选项")
    private List<Option> option;

    @ApiModelProperty(value = "题目解析")
    @ExcelProperty("题目解析")
    @TableField("`explain`")
    private String explain;

    @TableField(exist = false)
    @ApiModelProperty(value = "知识点id")
    private List<Integer> knowledgeId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Question question = (Question) o;
        return Objects.equals(questionId, question.questionId);
    }

    @Override
    public int hashCode() {
        return questionId == null ? 0 : questionId.hashCode();
    }
}