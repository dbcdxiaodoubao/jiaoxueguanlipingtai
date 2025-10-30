package com.mashang.domain.query.teacher;

import com.mashang.domain.query.common.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("学生分页查询条件")
public class StudentPageQuery extends PageQuery {

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("年级")
    private Integer grade;
}
