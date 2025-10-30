package com.mashang.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

import com.mashang.domain.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 班级表
 * @TableName ms_class
 */
@TableName(value ="ms_class")
@Data
@Accessors(chain = true)
@ApiModel(value="Class对象", description="班级表")
public class Class extends BaseModel  {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "班级id")
    @TableId(value = "class_id", type = IdType.AUTO)
    private Integer classId;

    @ApiModelProperty(value = "班级名称")
    private String className;

    @ApiModelProperty(value = "班级年级（1-12分别代表一年级到高三）")
    private Integer grade;

    @ApiModelProperty(value = "班级教师id")
    private Long teacherId;

    @ApiModelProperty(value = "班级人数")
    private Integer classSize;

    @ApiModelProperty(value = "班级口令")
    private String classPassword;
}