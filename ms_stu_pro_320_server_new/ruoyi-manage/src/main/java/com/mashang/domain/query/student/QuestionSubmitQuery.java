package com.mashang.domain.query.student;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QuestionSubmitQuery {

    @ApiModelProperty(value = "答题id")
    @TableId(value = "question_answer_id", type = IdType.AUTO)
    private Integer questionAnswerId;

    @ApiModelProperty(value = "用户答案")
    private String userAnswer;

    @ApiModelProperty(value = "题目类型")
    private Integer questionType;


}
