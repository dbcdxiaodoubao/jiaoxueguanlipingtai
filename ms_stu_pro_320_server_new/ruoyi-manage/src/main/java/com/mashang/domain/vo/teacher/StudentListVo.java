package com.mashang.domain.vo.teacher;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Data
@ApiModel("学生列表vo")
@Alias("TeacherStudentListVo")
public class StudentListVo {

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("真实姓名")
    private String NickName;

    @ApiModelProperty("年级")
    private Integer grade;

    @ApiModelProperty("性别")
    private Integer sex;

    @ApiModelProperty("手机号")
    private String phonenumber;

    @ApiModelProperty("加入班级时间")
    private Date joinClassTime;

    @ApiModelProperty("班级名称")
    private String className;
}
