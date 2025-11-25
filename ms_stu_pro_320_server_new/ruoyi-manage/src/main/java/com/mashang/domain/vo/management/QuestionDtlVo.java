package com.mashang.domain.vo.management;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.mashang.domain.model.BaseModel;
import com.mashang.domain.model.Option;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Objects;


@Data
@ApiModel(value="问题详情")
public class QuestionDtlVo  {

    @ApiModelProperty(value = "学科id")
    @ExcelProperty("学科id")
    private Integer subjectId;

    @ApiModelProperty(value = "题目分数")
    @ExcelProperty("题目分数")
    private Integer questionScore;

    @ApiModelProperty(value = "题目难度（1-10）")
    @ExcelProperty("题目难度（1-10）")
    private Integer questionDifficulty;

    @ApiModelProperty(value = "题目类型（0 单选 1 多选 2 判断 3 填空 4 简答）")
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

    @ApiModelProperty(value = "知识点id")
    private List<Integer> knowledgeId;
}