package com.mashang.domain.vo.teacher;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;
import java.util.List;

@Data
@ApiModel("教师端试卷列表vo")
@Alias("TestListVoTeacher")
public class TestListVo {

    @ApiModelProperty(value = "试卷主键")
    private Integer testId;

    @ApiModelProperty("学科名称")
    private String subjectName;

    @ApiModelProperty("年级")
    private Integer grade;

    @ApiModelProperty(value = "试卷名称")
    private String testName;

    @ApiModelProperty(value = "班级")
    private List<String> classNames;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
