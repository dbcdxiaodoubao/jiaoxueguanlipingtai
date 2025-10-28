package com.mashang.domain.query.management;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.mashang.domain.model.Option;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="试卷所关联的问题")
public class QuestionTestCreat {

    @ApiModelProperty(value = "大标题")
    private String bigTitle;

    @ApiModelProperty(value = "属于第几部分")
    private Integer order;

    @ApiModelProperty("题目id")
    private Long questionId;

}
