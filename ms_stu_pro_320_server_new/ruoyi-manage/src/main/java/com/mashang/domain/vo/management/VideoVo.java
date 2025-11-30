package com.mashang.domain.vo.management;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("视频详情vo")
public class VideoVo {

    @ApiModelProperty("视频id")
    private Integer videoId;

    @ApiModelProperty("学科名称")
    private String subjectName;

    @ApiModelProperty(value = "试卷名称")
    private String testName;

    /**
     * 视频路径
     */
    @ApiModelProperty("视频路径")
    private String videoUrl;

    /**
     * 封面路径
     */
    @ApiModelProperty("封面路径")
    private String coverUrl;

    /**
     * 年级
     */
    @ApiModelProperty("年级")
    private String grade;

    /**
     * 视频名称
     */
    @ApiModelProperty("视频名称")
    private String videoName;
}
