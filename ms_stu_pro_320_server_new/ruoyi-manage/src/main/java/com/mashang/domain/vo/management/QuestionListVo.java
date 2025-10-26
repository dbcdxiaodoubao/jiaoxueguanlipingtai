package com.mashang.domain.vo.management;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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

    @ApiModelProperty(value = "题目类型")
    @ExcelProperty("题目类型")
    private Integer questionType;

    @ApiModelProperty(value = "题目题干")
    @ExcelProperty("题目题干")
    private String questionTitle;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

}