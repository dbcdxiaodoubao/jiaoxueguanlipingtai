package com.mashang.domain.vo.student;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class VideoTestVo {

    @ApiModelProperty(value = "答卷id")
    private Long testAnswerId;

    @ApiModelProperty(value = "学科名称")
    private String subjectName;

    @ApiModelProperty(value = "试卷名称")
    private String testName;

    @ApiModelProperty("视频id")
    private Integer videoId;

    @ApiModelProperty("视频路径")
    private String videoUrl;

    @ApiModelProperty("封面路径")
    private String coverUrl;

    @ApiModelProperty("视频名称")
    private String videoName;

}
