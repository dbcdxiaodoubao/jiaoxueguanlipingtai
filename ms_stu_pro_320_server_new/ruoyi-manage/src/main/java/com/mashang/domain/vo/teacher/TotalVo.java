package com.mashang.domain.vo.teacher;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("查询班级总数，班级总人数，试卷总数，题目总数vo")
public class TotalVo {

    @ApiModelProperty("班级总数")
    private Integer classCount;

    @ApiModelProperty("班级总人数")
    private Integer classUserCount;

    @ApiModelProperty("试卷总数")
    private Integer testCount;

    @ApiModelProperty("题目总数")
    private Integer questionCount;
}
