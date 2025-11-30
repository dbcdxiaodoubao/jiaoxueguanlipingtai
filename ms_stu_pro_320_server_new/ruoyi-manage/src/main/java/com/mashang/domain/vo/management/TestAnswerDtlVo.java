package com.mashang.domain.vo.management;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("答卷详情vo")
public class TestAnswerDtlVo {

    @ApiModelProperty(value = "任务名称")
    private String taskName;

    @ApiModelProperty(value = "答卷id")
    private Long testAnswerId;

    @ApiModelProperty(value = "试卷名称")
    private String testName;

    @ApiModelProperty(value = "答卷人")
    private String nickName;

    @ApiModelProperty(value = "答卷状态（0待批改 1已完成 2未完成）")
    private Integer status;

    @ApiModelProperty(value = "试卷总分")
    private Integer sumScore;

    @ApiModelProperty(value = "考试用时（秒）")
    private Integer duration;

    @ApiModelProperty(value = "试卷得分")
    private Integer userTestScore;

    @ApiModelProperty(value = "答题列表")
    private List<TestAnswerQuestionAnswerVo> questionAnswers;

}
