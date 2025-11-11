package com.mashang.domain.vo.student;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SubjectsListByGradeVo {

    @ApiModelProperty("学科id")
    private Long subjectId;

    @ApiModelProperty("学科名称")
    private String subjectName;
}
