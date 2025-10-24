package com.mashang.domain.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mashang.domain.model.BaseModel;
import lombok.Data;

@TableName(value ="ms_subjects")
@Data
public class Subjects extends BaseModel {

  @TableId(type = IdType.AUTO)
  private Integer subjectId;
  private String subjectName;
  private Integer grade;

}