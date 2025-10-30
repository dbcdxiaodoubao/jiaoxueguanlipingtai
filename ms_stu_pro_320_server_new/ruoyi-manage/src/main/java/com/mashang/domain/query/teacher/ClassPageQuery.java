package com.mashang.domain.query.teacher;

import com.mashang.domain.query.common.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("教师班级分页查询条件")
public class ClassPageQuery extends PageQuery {

    @ApiModelProperty(value = "班级名称")
    private String className;
}
