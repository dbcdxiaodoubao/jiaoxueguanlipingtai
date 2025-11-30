package com.mashang.domain.param.teacher;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ApiModel("修改试卷参数")
public class TestUpdate {

    @ApiModelProperty(value = "试卷主键",required = true)
    @NotNull(message = "试卷id为空")
    private Integer testId;

    @ApiModelProperty(value = "试卷名称",required = true)
    @NotNull(message = "试卷名称为空")
    private String testName;

    @ApiModelProperty(value = "学科id",required = true)
    @NotNull(message = "学科id为空")
    private Integer subjectId;

    @ApiModelProperty(value = "试卷类型",required = true)
    @NotNull(message = "试卷类型为空")
    private Integer testType;

    @ApiModelProperty(value = "建议考试时长（分钟）",required = true)
    @NotNull(message = "建议考试时长为空")
    private Integer suggestDuration;

    @ApiModelProperty(value = "绑定班级id集合",required = true)
    @NotNull(message = "绑定班级id集合为空")
    private List<Integer> classIds;
}
