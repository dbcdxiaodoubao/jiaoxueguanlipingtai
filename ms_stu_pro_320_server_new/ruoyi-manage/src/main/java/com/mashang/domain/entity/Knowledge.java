package com.mashang.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

import com.mashang.domain.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 知识点表
 * @TableName ms_knowledge
 */
@TableName(value ="ms_knowledge")
@Data
@ApiModel(value="Knowledge对象", description="知识点表")
public class Knowledge extends BaseModel {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "知识点id")
    @TableId(value = "knowledge_id", type = IdType.AUTO)
    private Integer knowledgeId;

    @ApiModelProperty(value = "知识点年级(1-12分别代表一年级到高三)")
    private Integer grade;

    @ApiModelProperty(value = "学科id")
    private Integer subjectId;

    @ApiModelProperty(value = "知识点名称")
    private String knowledgeName;

    @ApiModelProperty(value = "父节点id")
    private Integer parentId;
}