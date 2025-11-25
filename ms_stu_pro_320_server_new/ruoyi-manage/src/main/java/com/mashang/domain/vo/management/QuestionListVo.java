package com.mashang.domain.vo.management;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value="题目信息列表")
public class QuestionListVo {

    @ApiModelProperty("题目id")
    private Long questionId;

    @ApiModelProperty(value = "年级")
    private Integer grade;

    @ApiModelProperty("学科")
    private String subject;

    @ApiModelProperty(value = "题目分数")
    @ExcelProperty("题目分数")
    private Integer questionScore;

    @ApiModelProperty(value = "题目难度（1-10）")
    @ExcelProperty("题目难度（1-10）")
    private Integer questionDifficulty;

    @ApiModelProperty(value = "题目类型（0 单选 1 多选 2 判断 3 填空 4 简答）")
    @ExcelProperty("题目类型")
    private Integer questionType;

    @ApiModelProperty(value = "题目题干")
    @ExcelProperty("题目题干")
    private String questionTitle;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

}