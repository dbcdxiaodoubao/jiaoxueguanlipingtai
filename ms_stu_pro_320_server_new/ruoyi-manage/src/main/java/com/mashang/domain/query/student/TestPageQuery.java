package com.mashang.domain.query.student;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TestPageQuery {
    @ApiModelProperty(value = "试卷类型")
    private Integer testType;

    @ApiModelProperty(value = "学科id")
    private Integer subjectId;

    @ApiModelProperty(value = "答卷人id")
    private Long userId;
}
