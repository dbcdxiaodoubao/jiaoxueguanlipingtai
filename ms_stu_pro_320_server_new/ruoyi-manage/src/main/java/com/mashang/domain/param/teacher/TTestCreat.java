package com.mashang.domain.param.teacher;

import com.mashang.domain.query.management.QuestionTestCreat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ApiModel(value="试卷创建")
@Alias("TTestCreat")
public class TTestCreat {

    @ApiModelProperty(value = "试卷名称",required = true)
    @NotBlank(message = "试卷名称不能为空")
    private String testName;

    @ApiModelProperty(value =  "年级",required = true)
    @NotNull(message = "年级不能为空")
    private Integer grade;

    @ApiModelProperty(value = "学科id",required = true)
    @NotNull(message = "学科不能为空")
    private Integer subjectId;

    @ApiModelProperty(value = "试卷类型（0固定 1时段 2班级 3视频 4随机 5任务）",required = true)
    @NotNull(message = "试卷类型不能为空")
    private Integer testType;

    @ApiModelProperty(value = "建议考试时长（分钟）",required = true)
    @NotNull(message = "建议时长不能为空")
    private Integer suggestDuration;

    @ApiModelProperty(value = "关联题目",required = true)
    @NotEmpty(message = "题目不能为空")
    @Valid
    private List<QuestionTestCreat> question;

    @ApiModelProperty(value = "绑定班级id集合")
    private List<Integer> classIds;
}