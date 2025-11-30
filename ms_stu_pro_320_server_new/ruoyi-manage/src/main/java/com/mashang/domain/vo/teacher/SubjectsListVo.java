package com.mashang.domain.vo.teacher;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@ApiModel("学科列表vo")
@Alias("TeacherSubjectsListVo")
public class SubjectsListVo {

    @ApiModelProperty("学科id")
    private Integer subjectId;

    @ApiModelProperty("学科名称")
    private String subjectName;
}
