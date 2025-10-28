package com.mashang.domain.query.manage;

import com.mashang.domain.query.common.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("答卷分页查询")
public class TestAnswerPageQuery extends PageQuery {

    @ApiModelProperty(value = "学科id")
    private Integer subjectId;

    @ApiModelProperty(value = "班级id")
    private Integer classId;
}
