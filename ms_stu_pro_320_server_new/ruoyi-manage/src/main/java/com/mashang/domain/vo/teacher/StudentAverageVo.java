package com.mashang.domain.vo.teacher;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ApiModel("学生平均分vo")
@AllArgsConstructor
@NoArgsConstructor
public class StudentAverageVo {

    @ApiModelProperty(value = "学生名称")
    private String studentName;

    @ApiModelProperty(value = "学生平均分")
    private Double averageScore;
}
