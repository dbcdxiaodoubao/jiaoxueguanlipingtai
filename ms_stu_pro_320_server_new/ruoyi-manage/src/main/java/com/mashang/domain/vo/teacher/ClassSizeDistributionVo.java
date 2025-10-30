package com.mashang.domain.vo.teacher;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("班级人数分布vo")
public class ClassSizeDistributionVo {

    @ApiModelProperty("班级名称")
    private String className;

    @ApiModelProperty("班级人数")
    private Integer classUserCount;
}
