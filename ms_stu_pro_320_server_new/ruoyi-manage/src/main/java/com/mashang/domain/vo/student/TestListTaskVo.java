package com.mashang.domain.vo.student;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TestListTaskVo {

    @ApiModelProperty(value = "试卷主键")
    private Integer testId;

    @ApiModelProperty(value = "试卷名称")
    private String testName;

}
