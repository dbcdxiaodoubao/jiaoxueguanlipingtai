package com.mashang.domain.query.student;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

@Data
public class VideoTestPageQuery {
    @ApiModelProperty("学科id")
    private Long subjectId;

    private Long userId;
}
