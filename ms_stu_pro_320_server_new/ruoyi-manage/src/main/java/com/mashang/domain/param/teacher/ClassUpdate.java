package com.mashang.domain.param.teacher;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel("班级修改参数")
public class ClassUpdate {

    @ApiModelProperty(value = "班级id",required = true)
    @NotNull(message = "班级id为空")
    private Integer classId;

    @ApiModelProperty(value = "班级名称",required = true)
    @NotNull(message = "班级名称为空")
    private String className;
}
