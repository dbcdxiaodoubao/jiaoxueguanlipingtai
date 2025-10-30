package com.mashang.domain.vo.teacher;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("教师班级列表vo")
public class TeacherClassListVo {

    @ApiModelProperty("班级id")
    private Integer classId;

    @ApiModelProperty("班级名称")
    private String className;
}
