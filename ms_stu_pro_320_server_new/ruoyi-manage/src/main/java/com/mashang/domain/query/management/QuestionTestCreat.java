package com.mashang.domain.query.management;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.mashang.domain.model.Option;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(value="试卷所关联的问题")
public class QuestionTestCreat {

    @ApiModelProperty(value = "大标题")
    private String bigTitle;

    @ApiModelProperty(value = "属于第几部分")
    private Integer order;

    @ApiModelProperty("题目id")
    @NotNull(message = "题目id不能为空")
    @Min(value = 1, message = "题目id不能为0")
    private Long questionId;

}
