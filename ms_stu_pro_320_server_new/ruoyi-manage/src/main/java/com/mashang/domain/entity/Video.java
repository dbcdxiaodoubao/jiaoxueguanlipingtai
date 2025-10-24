package com.mashang.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

import com.mashang.domain.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

/**
 * 
 * @TableName ms_video
 */
@TableName(value ="ms_video")
@Data
public class Video extends BaseModel {
    /**
     * 视频id
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("视频id")
    private Integer videoId;

    /**
     * 学科id
     */
    @ApiModelProperty("学科id")
    private Integer subjectId;

    /**
     * 试卷id
     */
    @ApiModelProperty("试卷id")
    private Integer testId;

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