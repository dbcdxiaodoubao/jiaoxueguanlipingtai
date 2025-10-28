package com.mashang.domain.vo.management;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.mashang.domain.model.Option;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="试卷中的题目")
public class QuestionTestVo {

    @ApiModelProperty(value = "大标题")
    private String bigTitle;

    @ApiModelProperty(value = "属于第几部分")
    private Integer order;

    @ApiModelProperty(value = "题目题干")
    private String questionTitle;

    @ApiModelProperty(value = "题目选项")
    @TableField(value = "`option`",typeHandler = JacksonTypeHandler.class)
    private Option option;
}
