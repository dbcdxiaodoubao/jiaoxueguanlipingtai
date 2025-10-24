package com.mashang.domain.vo.management;


import com.mashang.domain.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("学科信息列表")
public class SubjectsListVo {

  @ApiModelProperty("学科id")
  private Integer subjectId;

  @ApiModelProperty("学科名称")
  private String subjectName;

  @ApiModelProperty("年级")
  private Integer grade;

}
