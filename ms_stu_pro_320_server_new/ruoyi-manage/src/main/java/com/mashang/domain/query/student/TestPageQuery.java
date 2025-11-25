package com.mashang.domain.query.student;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;

@Data
public class TestPageQuery {
    @ApiModelProperty(value = "试卷类型（0固定 1时段 2班级 3视频 4随机 5任务）",required = true)
    @NotNull(message = "试卷类型不能为空")
    private Integer testType;

    @ApiModelProperty(value = "学科id",required = true)
    @NotNull(message = "学科id不能为空")
    private Long subjectId;

}
