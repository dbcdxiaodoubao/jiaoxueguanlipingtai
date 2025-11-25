package com.mashang.domain.query.student;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class TestSubmitQuery {

    @ApiModelProperty(value = "答卷id",required = true)
    @NotNull(message = "答卷id不能为空")
    private Long testAnswerId;

    @ApiModelProperty(value = "考试用时（秒）",notes = "不能超过建议时长(随机试卷除外)")
    private Integer duration;

    @ApiModelProperty(value = "题目集合（题目放在这个里面）",required = true)
    @Valid
    private List<QuestionSubmitQuery> questionSubmits;

}
