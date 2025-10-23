package com.mashang.domain.vo.student;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TestAnswerListTaskVo {

    @ApiModelProperty(value = "答卷id")
    @TableId(value = "test_answer_id", type = IdType.AUTO)
    private Integer testAnswerId;

    @ApiModelProperty(value = "答卷状态（0待批改 1已完成 2未完成）")
    private Integer status;

    @ApiModelProperty(value = "试卷名称")
    private String testName;

}
