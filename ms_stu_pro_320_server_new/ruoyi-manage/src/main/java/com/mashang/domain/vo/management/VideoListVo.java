package com.mashang.domain.vo.management;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("视频列表vo")
public class VideoListVo {

    @ApiModelProperty("视频id")
    private Integer videoId;

    @ApiModelProperty("学科名称")
    private String subjectName;

    @ApiModelProperty(value = "试卷名称")
    private String testName;

    @ApiModelProperty("年级")
    private String grade;

    @ApiModelProperty("视频名称")
    private String videoName;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
}
