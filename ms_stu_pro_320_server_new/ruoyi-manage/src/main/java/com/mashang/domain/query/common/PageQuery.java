package com.mashang.domain.query.common;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@ApiModel("分页实体")
@Data
public class PageQuery {

    @ApiModelProperty("分页大小")
    @NotNull(message = "分页大小不能为空")
    private Integer pageSize;

    @ApiModelProperty("第几页")
    @NotNull(message = "第几页不能为空")
    private Integer pageNum;
}
