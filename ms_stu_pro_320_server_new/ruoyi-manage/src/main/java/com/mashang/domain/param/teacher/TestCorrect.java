package com.mashang.domain.param.teacher;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TestCorrect {

    @ApiModelProperty(value = "答题id",required = true)
    @NotNull(message = "答题id为空")
    private Integer questionAnswerId;

    @ApiModelProperty(value = "学生得分",required = true)
    @NotNull(message = "学生得分为空")
    private Integer userQuestionScore;

    @ApiModelProperty(value = "答题状态",required = true)
    @NotNull(message = "答题状态为空")
    private Integer status;
}
