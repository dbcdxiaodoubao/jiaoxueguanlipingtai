package com.mashang.domain.query.management;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.mashang.domain.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@ApiModel("修改学科实体")
public class SubjectsUpdate {

    @ApiModelProperty("学科id")
    private Integer subjectId;

  @ApiModelProperty(value = "学科名称" , required = true)
  @NotBlank(message = "学科名称不能为空")
  private String subjectName;

  @ApiModelProperty(value = "年级", required = true)
  @NotNull(message = "年级信息不能为空")
  private Integer grade;

}