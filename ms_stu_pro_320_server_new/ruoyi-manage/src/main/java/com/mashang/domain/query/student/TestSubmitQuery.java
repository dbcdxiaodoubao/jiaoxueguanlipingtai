package com.mashang.domain.query.student;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class TestSubmitQuery {

    @ApiModelProperty(value = "答卷id")
    private Long testAnswerId;

    @ApiModelProperty(value = "考试用时（秒）")
    private Integer duration;

    @ApiModelProperty(value = "题目集合")
    private List<QuestionSubmitQuery> questionSubmits;

}
