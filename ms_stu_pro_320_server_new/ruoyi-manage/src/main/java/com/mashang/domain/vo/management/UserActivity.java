package com.mashang.domain.vo.management;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Date;

@Data
@ApiModel("用户活跃度")
public class UserActivity {

    @ApiModelProperty("日期")
    private Date date;

    @ApiModelProperty("登录用户数")
    private Integer userSum;
}
