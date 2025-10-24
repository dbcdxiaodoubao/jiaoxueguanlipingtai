package com.mashang.domain.vo.student;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mashang.domain.model.Option;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestionAnswerVo {

    @TableId(value = "question_answer_id", type = IdType.AUTO)
    @ApiModelProperty(value = "答题id")
    private Integer questionAnswerId;


    @TableField(value = "`option`",typeHandler = JacksonTypeHandler.class)
    @ApiModelProperty(value = "题目选项")
    private Option option;

    @ApiModelProperty(value = "题目类型")
    private Integer questionType;

    @ApiModelProperty(value = "题目题干")
    private String questionTitle;

    @ApiModelProperty(value = "用户答案")
    private String userAnswer;

    @ApiModelProperty(value = "答题状态（0待批改 1正确 2错误）")
    private Integer status;

    @ApiModelProperty(value = "题目答案")
    private String rightAnswer;

}
