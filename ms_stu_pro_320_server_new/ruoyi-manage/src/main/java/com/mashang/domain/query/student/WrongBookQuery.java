package com.mashang.domain.query.student;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class WrongBookQuery {
    @ApiModelProperty(value = "学科id")
    private Integer subjectId;

    @ApiModelProperty(value = "答卷人id")
    private Long userId;
}
