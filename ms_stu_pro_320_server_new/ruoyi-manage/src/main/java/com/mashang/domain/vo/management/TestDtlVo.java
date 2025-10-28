package com.mashang.domain.vo.management;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.mashang.domain.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
@ApiModel(value="试卷详情")
public class TestDtlVo  {

    @ApiModelProperty(value = "试卷名称")
    private String testName;

    @ApiModelProperty("年级")
    private Integer grade;

    @ApiModelProperty(value = "学科id")
    private Integer subjectId;

    @ApiModelProperty(value = "试卷类型")
    private Integer testType;

    @ApiModelProperty(value = "建议考试时长（分钟）")
    private Integer suggestDuration;

    @ApiModelProperty(value = "题目")
    private List<QuestionTestVo> question;
}