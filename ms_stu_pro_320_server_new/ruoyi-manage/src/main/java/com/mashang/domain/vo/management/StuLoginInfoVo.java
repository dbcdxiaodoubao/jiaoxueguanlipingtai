package com.mashang.domain.vo.management;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Date;

@Data
@ApiModel("学生日志信息")
public class StuLoginInfoVo {

  @ApiModelProperty("日志id")
  private Integer infoId;
  @ApiModelProperty("登录IP地址")
  private String ipaddr;
  @ApiModelProperty("登录地点")
  private String loginLocation;
  @ApiModelProperty("浏览器类型")
  private String browser;
  @ApiModelProperty("操作系统")
  private String os;
  @ApiModelProperty("登录状态 0成功 1失败")
  private String status;
  @ApiModelProperty("提示信息")
  private String msg;
  @ApiModelProperty("访问时间")
  private Date loginTime;

}
