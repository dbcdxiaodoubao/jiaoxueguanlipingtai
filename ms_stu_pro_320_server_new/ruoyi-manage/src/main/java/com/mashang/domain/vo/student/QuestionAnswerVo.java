package com.mashang.domain.vo.student;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.mashang.domain.model.Option;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QuestionAnswerVo {
    @ApiModelProperty(value = "答题id")
    @TableId(value = "question_answer_id", type = IdType.AUTO)
    private Integer questionAnswerId;

    @ApiModelProperty(value = "题目选项")
    @TableField(value = "`option`",typeHandler = JacksonTypeHandler.class)
    private Option option;

    @ApiModelProperty(value = "题目类型")
    private Integer questionType;

    @ApiModelProperty(value = "题目题干")
    private String questionTitle;

}
