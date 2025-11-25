package com.mashang.domain.query.management;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@ApiModel(value="修改知识点")
public class KnowledgeUpdate {

    @ApiModelProperty(value = "知识点id")
    private Integer knowledgeId;

    @ApiModelProperty(value = "知识点年级(1-12分别代表一年级到高三)", required = true)
    @NotNull(message = "年级不能为空")
    private Integer grade;

    @ApiModelProperty(value = "学科id")
    @NotNull(message = "学科id不能为空")
    private Integer subjectId;

    @ApiModelProperty(value = "知识点名称", required = true)
    @NotBlank(message = "知识点名称不能为空")
    private String knowledgeName;

    @ApiModelProperty(value = "父节点id (0代表为一级知识点)", required = true)
    @NotNull(message = "父节点id不能为空")
    private Integer parentId;
}