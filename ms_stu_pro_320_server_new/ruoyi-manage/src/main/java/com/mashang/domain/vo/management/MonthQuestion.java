package com.mashang.domain.vo.management;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Date;

@Data
@ApiModel("题目月数量")
public class MonthQuestion {

    @ApiModelProperty("日期")
    private Date date;

    @ApiModelProperty("题目数量")
    private Integer questionSum;
}
