package com.mashang.domain.vo.teacher;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Data
@ApiModel("教师端试卷详情vo")
@Alias("TestDtlVoTeacher")
public class TestDtlVo {

    @ApiModelProperty(value = "试卷主键")
    private Integer testId;

    @ApiModelProperty(value = "试卷名称")
    private String testName;

    @ApiModelProperty("学科名称")
    private String subjectName;

    @ApiModelProperty(value = "班级列表")
    private List<Integer> classIds;

    @ApiModelProperty(value = "试卷类型")
    private Integer testType;

    @ApiModelProperty(value = "建议考试时长（分钟）")
    private Integer suggestDuration;
}
