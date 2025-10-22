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
 * 知识点-题目表
 * @TableName ms_knowledge_question
 */
@TableName(value ="ms_knowledge_question")
@Data
@ApiModel(value="KnowledgeQuestion对象", description="知识点-题目表")
public class KnowledgeQuestion extends BaseModel {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "知识点-主键id")
    @TableId(value = "knowledge_question_id", type = IdType.AUTO)
    private Integer knowledgeQuestionId;

    @ApiModelProperty(value = "知识点id")
    private Integer knowledgeId;

    @ApiModelProperty(value = "题目id")
    private Integer questionId;
}