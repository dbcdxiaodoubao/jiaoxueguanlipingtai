package com.mashang.domain.query.student;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class QuestionSubmitQuery {

    @ApiModelProperty(value = "答题id",required = true)
    @TableId(value = "question_answer_id", type = IdType.AUTO)
    @NotNull(message = "答题id不能为空")
    private Integer questionAnswerId;

    @ApiModelProperty(value = "用户答案")
    private String userAnswer;

    @ApiModelProperty(value = "题目类型",required = true)
    @NotNull(message = "题目类型不能为空")
    private Integer questionType;


}
