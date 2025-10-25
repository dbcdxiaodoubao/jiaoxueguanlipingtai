package com.mashang.domain.vo.management;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mashang.domain.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 知识点表
 * @TableName ms_knowledge
 */
@Data
@ApiModel(value="知识点列表")
public class KnowledgeListVo {

    @ApiModelProperty(value = "学科id")
    private Integer subjectId;

    @ApiModelProperty("学科名称")
    private String subjectName;

    @ApiModelProperty(value = "知识点年级(1-12分别代表一年级到高三)")
    private Integer grade;

    @ApiModelProperty("一级知识点数量")
    private Integer FirstLevelKnowledge;
}