package com.mashang.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

import com.mashang.domain.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * @TableName ms_random_test_question
 */
@TableName(value ="ms_random_test_question")
@Data
public class RandomTestQuestion extends BaseModel {
    /**
     * 随机试卷-问题id
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("随机试卷-问题id")
    private Long randomTestQuestionId;

    /**
     * 问题id
     */
    @ApiModelProperty("问题id")
    private Long questionId;

    /**
     * 随机试卷id
     */
    @ApiModelProperty("随机试卷id")
    private Long randomTestId;

}