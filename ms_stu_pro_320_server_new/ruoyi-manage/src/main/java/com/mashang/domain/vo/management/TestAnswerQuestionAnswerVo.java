package com.mashang.domain.vo.management;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.mashang.domain.model.Option;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("答卷详情答题vo")
public class TestAnswerQuestionAnswerVo {

    @ApiModelProperty(value = "答题id")
    private Integer questionAnswerId;

    @ApiModelProperty(value = "题目题干")
    private String questionTitle;

    @ApiModelProperty(value = "题目难度")
    private Integer questionDifficulty;

    @ApiModelProperty(value = "用户答案")
    private String userAnswer;

    @ApiModelProperty(value = "题目解析")
    private String explain;

    @ApiModelProperty(value = "答题状态（0待批改 1正确 2错误）")
    private Integer status;

    @ApiModelProperty(value = "题目答案")
    private String rightAnswer;

    @ApiModelProperty(value = "题目分数")
    private Integer questionScore;

    @ApiModelProperty(value = "题目选项")
    private Option option;

    @ApiModelProperty(value = "题目类型")
    private Integer questionType;
}
