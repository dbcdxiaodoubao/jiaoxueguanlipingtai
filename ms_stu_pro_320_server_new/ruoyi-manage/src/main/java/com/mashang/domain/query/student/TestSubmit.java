package com.mashang.domain.query.student;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class TestSubmit {
    @ApiModelProperty(value = "答卷id")
    @TableId(value = "test_answer_id", type = IdType.AUTO)
    private Long testAnswerId;

    @ApiModelProperty(value = "考试用时（秒）")
    private Integer duration;

    @ApiModelProperty(value = "试卷得分")
    private Integer userTestScore;

    @ApiModelProperty(value = "题目集合")
    private List<QuestionSubmit> questionSubmits;

}
