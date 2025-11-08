package com.mashang.domain.vo.student;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Data
public class TestListVo {

    @ApiModelProperty(value = "答卷id")
    @TableId(value = "test_answer_id", type = IdType.AUTO)
    private Integer testAnswerId;

    @ApiModelProperty(value = "试卷名称")
    private String testName;

    @ApiModelProperty(value = "试卷发布时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @ApiModelProperty(value = "截止时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date deadline;

    @ApiModelProperty(value = "试卷总分")
    private Integer sumScore;

    @ApiModelProperty(value = "学科名称")
    private String subjectName;

    @ApiModelProperty(value = "题目总数")
    private Integer questionNum;

    @ApiModelProperty(value = "建议考试时长（分钟）")
    private Integer suggestDuration;

    @ApiModelProperty(value = "答卷状态（0待批改 1已完成 2未完成）")
    private Integer status;
}
