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
 * @TableName ms_random_test
 */
@TableName(value ="ms_random_test")
@Data
public class RandomTest extends BaseModel {
    /**
     * 随机试卷id
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("随机试卷id")
    private Long randomTestId;

    /**
     * 试卷总分
     */
    @ApiModelProperty("试卷总分")
    private Integer testScore;

    /**
     * 题目总数
     */
    @ApiModelProperty("题目总数")
    private Integer questionNum;

    /**
     * 学科id
     */
    @ApiModelProperty("学科id")
    private Long subjectId;

    /**
     * 试卷名称
     */
    @ApiModelProperty("试卷名称")
    private String testName;

}