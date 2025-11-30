package com.mashang.domain.vo.teacher;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("班级列表vo")
public class TAClassListVo {

    @ApiModelProperty(value = "班级id")
    private Integer classId;

    @ApiModelProperty(value = "班级名称")
    private String className;
}
