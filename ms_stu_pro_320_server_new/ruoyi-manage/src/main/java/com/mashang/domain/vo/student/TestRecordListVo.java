package com.mashang.domain.vo.student;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class TestRecordListVo {

    @ApiModelProperty(value = "答卷id")
    private Long testAnswerId;

    @ApiModelProperty(value = "试卷名称")
    private String testName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "提交时间")
    private Date submitTime;

    @ApiModelProperty(value = "答卷状态（0待批改 1已完成 2未完成）")
    private Integer status;

    @ApiModelProperty(value = "试卷类型（0固定 1时段 2班级 3视频 4随机 5任务）")
    private Integer testType;

    @ApiModelProperty(value = "学科名称")
    private String subjectName;

    @ApiModelProperty(value = "题目总数")
    private Integer questionNum;

    @ApiModelProperty(value = "试卷总分")
    private Integer sumScore;

    @ApiModelProperty(value = "发布时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiModelProperty(value = "正确题数")
    private Integer rightNum;

    @ApiModelProperty(value ="自动批改")
    private Integer autoChecking;

    @ApiModelProperty(value = "最终得分")
    private Integer finalScore;

    @ApiModelProperty(value = "考试用时（秒）")
    private Integer duration;

}
