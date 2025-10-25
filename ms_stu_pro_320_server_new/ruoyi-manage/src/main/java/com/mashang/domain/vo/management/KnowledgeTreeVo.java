package com.mashang.domain.vo.management;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mashang.domain.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


@Data
@ApiModel(value="知识点树")
public class KnowledgeTreeVo  {

    @ApiModelProperty(value = "知识点id")
    private Integer knowledgeId;

    @ApiModelProperty(value = "知识点名称")
    private String knowledgeName;

    @ApiModelProperty(value = "父节点id (0代表为一级知识点)")
    private Integer parentId;

    @ApiModelProperty("孩子节点")
    private List<KnowledgeTreeVo> children;
}