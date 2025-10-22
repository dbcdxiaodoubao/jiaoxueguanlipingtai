package com.mashang.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

import com.mashang.domain.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 学科表
 * @TableName ms_subjects
 */
@TableName(value ="ms_subjects")
@Data
@ApiModel(value="Subjects对象", description="学科表")
public class Subjects extends BaseModel {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "学科id")
    @TableId(value = "subject_id", type = IdType.AUTO)
    private Integer subjectId;

    @ApiModelProperty(value = "学科名称")
    private String subjectName;

    @ApiModelProperty(value = "学科年级(1-12分别代表一年级到高三)")
    private Integer grade;
}