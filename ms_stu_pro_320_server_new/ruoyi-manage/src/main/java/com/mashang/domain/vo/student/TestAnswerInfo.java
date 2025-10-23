package com.mashang.domain.vo.student;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class TestAnswerInfo {
    @ApiModelProperty(value = "答卷id")
    @TableId(value = "test_answer_id", type = IdType.AUTO)
    private Long testAnswerId;

    @ApiModelProperty(value = "试卷总分")
    private Integer sumScore;

    @ApiModelProperty(value = "试卷名称")
    private String testName;

    @ApiModelProperty(value = "建议考试时长（分钟）")
    private Integer suggestDuration;

    @ApiModelProperty(value = "题目总数")
    private Integer questionNum;

    @ApiModelProperty(value = "题目集合")
    private List<QuestionAnswerVo> questionVos;
}
