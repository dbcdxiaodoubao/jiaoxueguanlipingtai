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
 * 试卷-班级表
 * @TableName ms_test_class
 */
@TableName(value ="ms_test_class")
@Data
@Accessors(chain = true)
@ApiModel(value="TestClass对象", description="试卷-班级表")
public class TestClass extends BaseModel {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "试卷-班级id")
    @TableId(value = "test_class_id", type = IdType.AUTO)
    private Integer testClassId;

    @ApiModelProperty(value = "试卷id")
    private Integer testId;

    @ApiModelProperty(value = "班级id")
    private Integer classId;
}