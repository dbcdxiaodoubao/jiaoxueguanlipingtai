package com.mashang.domain.vo.teacher;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel(value = "班级详情vo")
public class ClassDtlVo {

    @ApiModelProperty(value = "班级id")
    private Integer classId;

    @ApiModelProperty(value = "班级名称")
    private String className;
}
