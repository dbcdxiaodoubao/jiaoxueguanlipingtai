package com.mashang.domain.vo.management;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


@Data
@ApiModel(value="试卷信息列表")
public class ManageTestListVo {


    @ApiModelProperty(value = "试卷主键")
    @TableId(value = "test_id", type = IdType.AUTO)
    private Integer testId;

    @ApiModelProperty(value = "试卷名称")
    private String testName;

    @ApiModelProperty("学科")
    private String subjectName;

    @ApiModelProperty(value = "年级")
    private Integer grade;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

}