package com.mashang.domain.vo.teacher;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("教师端查询试卷详情返回的班级列表")
public class TestClassListVo {

    @ApiModelProperty("班级id")
    private Integer classId;

    @ApiModelProperty("班级名称")
    private String className;
}
