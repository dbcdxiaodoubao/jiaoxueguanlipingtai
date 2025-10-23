package com.mashang.domain.query.common;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("分页实体")
@Data
public class PageQuery {

    @ApiModelProperty("分页大小")
    private Integer pageSize;

    @ApiModelProperty("第几页")
    private Integer pageNum;
}
