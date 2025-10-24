package com.mashang.domain.vo.management;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("学科详情")
public class SubjectsDtlVo {

  @ApiModelProperty("学科名称")
  private String subjectName;

  @ApiModelProperty("年级")
  private Integer grade;

}
