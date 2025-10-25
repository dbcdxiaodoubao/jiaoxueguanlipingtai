package com.mashang.domain.query.manage;

import com.mashang.domain.query.common.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("任务分页查询")
public class TaskPageQuery extends PageQuery {

    @ApiModelProperty(value = "班级年级（1-12分别代表一年级到高三）")
    private Integer grade;
}
