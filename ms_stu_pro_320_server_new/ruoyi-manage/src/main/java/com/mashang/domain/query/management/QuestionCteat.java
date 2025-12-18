package com.mashang.domain.query.management;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.mashang.domain.model.Option;
import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;


@Data
@ApiModel(value="问题创建")
public class QuestionCteat {

    @ApiModelProperty(value = "学科id" , required = true)
    @Excel(name = "学科id")
    @NotNull(message = "学科id不能为空")
    private Integer subjectId;

    @ApiModelProperty(value = "题目分数", required = true)
    @Excel(name = "题目分数")
    @NotNull(message = "题目分数不能为空")
    private Integer questionScore;

    @ApiModelProperty(value = "题目难度（1-5）", required = true)
    @Excel(name = "题目难度（1-5）")
    @NotNull(message = "题目难度不能为空")
    private Integer questionDifficulty;

    @ApiModelProperty(value = "题目类型", required = true)
    @Excel(name = "题目类型")
    @NotNull(message = "题目类型不能为空")
    private Integer questionType;

    @ApiModelProperty(value = "题目题干", required = true)
    @Excel(name = "题目题干")
    @NotBlank(message = "题目题干不能为空")
    private String questionTitle;

    @ApiModelProperty(value = "题目答案", required = true)
    @Excel(name = "题目答案")
    @NotBlank(message = "题目答案不能为空")
    private String questionAnswer;

    @ApiModelProperty(value = "题目选项")
    @Excel(name = "题目选项(jason)")
    @TableField(value = "`option`",typeHandler = JacksonTypeHandler.class)
    private List<Option> option;

    @ApiModelProperty(value = "题目解析", required = true)
    @Excel(name = "题目解析")
    @TableField("`explain`")
    @NotBlank(message = "题目解析不能为空")
    private String explain;

    @Excel(name = "知识点id(数字用逗号隔开例如:1,2,3)")
    @ApiModelProperty(value = "知识点id")
    private List<Integer> knowledgeId;

}