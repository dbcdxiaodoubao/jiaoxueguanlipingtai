package com.mashang.domain.vo.student;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@ApiModel("登录日志")
@AllArgsConstructor
@NoArgsConstructor
public class LoginInfoVo {

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("登录时间")
    private List<Date> loginTime;
}
