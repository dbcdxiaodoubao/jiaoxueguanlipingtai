package com.mashang.domain.vo.student;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import java.util.Date;

@Data
@ApiOperation("学生个人信息")
public class StudentInfoVo {

    @ApiModelProperty("真实姓名")
    private String nickName;

    @ApiModelProperty(value = "年级（1-12代表一年级到高三）")
    private Long grade;

    @ApiModelProperty(value = "班级名称")
    private String className;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
