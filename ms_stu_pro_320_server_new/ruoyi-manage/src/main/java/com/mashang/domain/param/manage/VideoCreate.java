package com.mashang.domain.param.manage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel("添加视频参数")
public class VideoCreate {


    @ApiModelProperty(value = "学科id",required = true)
    @NotNull(message = "学科id为空")
    private Integer subjectId;

    /**
     * 试卷id
     */
    @ApiModelProperty(value = "试卷id",required = true)
    @NotNull(message = "试卷id为空")
    private Integer testId;

    /**
     * 视频路径
     */
    @ApiModelProperty(value = "视频路径",required = true)
    @NotNull(message = "视频路径为空")
    private String videoUrl;

    /**
     * 封面路径
     */
    @ApiModelProperty(value = "封面路径",required = true)
    @NotNull(message = "封面路径为空")
    private String coverUrl;

    /**
     * 年级
     */
    @ApiModelProperty(value = "年级",required = true)
    @NotNull(message = "年级为空")
    private String grade;

    /**
     * 视频名称
     */
    @ApiModelProperty(value = "视频名称",required = true)
    @NotNull(message = "视频名称为空")
    private String videoName;
}
