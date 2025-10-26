package com.mashang.domain.query.management;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.mashang.domain.model.BaseModel;
import com.mashang.domain.model.Option;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Objects;


@Data
@ApiModel(value="查询问题列表的条件")
public class QuestionListQuery {

    @ApiModelProperty("题目id")
    private Long questionId;

    @ApiModelProperty(value = "学科id")
    @ExcelProperty("学科id")
    private Integer subjectId;

    @ApiModelProperty(value = "年级")
    private Integer grade;

    @ApiModelProperty(value = "题目类型")
    @ExcelProperty("题目类型")
    private Integer questionType;


}