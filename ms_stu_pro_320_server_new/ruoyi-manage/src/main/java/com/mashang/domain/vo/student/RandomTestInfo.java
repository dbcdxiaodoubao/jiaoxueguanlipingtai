package com.mashang.domain.vo.student;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RandomTestInfo {

    /**
     * 随机试卷id
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("随机试卷id")
    private Long randomTestId;

    @ApiModelProperty(value = "试卷总分")
    private Integer sumScore;

    @ApiModelProperty(value = "试卷名称")
    private String testName;


    @ApiModelProperty(value = "题目总数")
    private Integer questionNum;

    @ApiModelProperty(value = "答卷状态（0待批改 1已完成 2未完成）")
    private Integer status;

    @ApiModelProperty(value = "考试用时（秒）")
    private Integer duration;

    @ApiModelProperty(value = "试卷得分")
    private Integer userTestScore;

    @ApiModelProperty(value = "题目集合")
    private List<QuestionAnswerVo> questionVos;
}
