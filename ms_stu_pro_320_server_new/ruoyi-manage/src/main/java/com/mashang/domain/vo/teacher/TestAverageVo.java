package com.mashang.domain.vo.teacher;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ApiModel("教师端班级平均分vo")
@AllArgsConstructor
@NoArgsConstructor
public class TestAverageVo {

    @ApiModelProperty(value = "班级名称")
    private String className;

    @ApiModelProperty(value = "班级平均分")
    private Double averageScore;
}
