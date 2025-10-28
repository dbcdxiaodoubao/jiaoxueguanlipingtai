package com.mashang.domain.vo.management;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("数据可视化实体")
public class ManegeData {

    @ApiModelProperty("试卷总数")
    private Long testSum;

    @ApiModelProperty("试卷总数")
    private Long questionSum;

    @ApiModelProperty("答卷总数")
    private Long testAnswerSum;

    @ApiModelProperty("答题总数")
    private Long answerSum;
}
