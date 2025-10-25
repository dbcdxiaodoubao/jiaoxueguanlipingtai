package com.mashang.domain.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mashang.domain.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@TableName(value ="ms_subjects")
@Data
public class Subjects extends BaseModel {

  @TableId(type = IdType.AUTO)
  @ApiModelProperty("学科id")
  private Integer subjectId;

  @ApiModelProperty("学科名称")
  private String subjectName;

  @ApiModelProperty("年级")
  private Integer grade;

}