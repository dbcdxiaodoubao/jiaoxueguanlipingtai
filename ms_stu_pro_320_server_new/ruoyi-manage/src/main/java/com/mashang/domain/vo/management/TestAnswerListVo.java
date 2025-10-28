package com.mashang.domain.vo.management;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


import java.util.Date;

@Data
@ApiModel("答卷列表vo")
public class TestAnswerListVo {

    @ApiModelProperty(value = "答卷id")
    private Long testAnswerId;

    @ApiModelProperty(value = "试卷名称")
    private String testName;

    @ApiModelProperty(value = "答卷人")
    private String nickName;

    @ApiModelProperty(value = "试卷得分")
    private Integer userTestScore;

    @ApiModelProperty(value = "试卷总分")
    private Integer sumScore;

    @ApiModelProperty(value = "考试用时（秒）")
    private Integer duration;

    @ApiModelProperty(value = "提交时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date submitTime;

    @ApiModelProperty(value = "正确答题数")
    private Integer rightCount;

    @ApiModelProperty(value = "总题数")
    private Integer questionCount;
}
