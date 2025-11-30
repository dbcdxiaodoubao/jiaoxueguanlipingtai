package com.mashang.domain.param.teacher;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ApiModel("主观题批改参数")
public class TestCorrectParam {

    @ApiModelProperty(value = "答卷id",required = true)
    @NotNull(message = "答卷id为空")
    private Integer testAnswerId;

    @ApiModelProperty(value = "主观题列表",required = true)
    @NotNull(message = "主观题列表为空")
    private List<TestCorrect> testCorrectList;
}
