package com.mashang.domain.vo.teacher;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("班级试卷分布vo")
public class ClassTestDistributionVo {

    @ApiModelProperty("班级名称")
    private String className;

    @ApiModelProperty("班级试卷数")
    private Integer classTestCount;
}
