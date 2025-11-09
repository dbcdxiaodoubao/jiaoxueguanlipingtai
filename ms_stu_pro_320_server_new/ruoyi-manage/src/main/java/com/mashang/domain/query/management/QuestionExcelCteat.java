package com.mashang.domain.query.management;

import cn.hutool.core.lang.TypeReference;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.mashang.domain.model.Option;
import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.*;


@Data
@ApiModel(value = "问题创建Excel导入DTO")
public class QuestionExcelCteat {

    @ApiModelProperty(value = "学科id", required = true)
    @Excel(name = "学科id")
    @NotNull(message = "学科id不能为空")
    private Integer subjectId;

    @ApiModelProperty(value = "题目分数", required = true)
    @Excel(name = "题目分数")
    @NotNull(message = "题目分数不能为空")
    private Integer questionScore;

    @ApiModelProperty(value = "题目难度（1-10）", required = true)
    @Excel(name = "题目难度（1-10）")
    @NotNull(message = "题目难度不能为空")
    private Integer questionDifficulty;

    @ApiModelProperty(value = "题目类型", required = true)
    @Excel(name = "题目类型")
    @NotNull(message = "题目类型不能为空")
    private Integer questionType;

    @ApiModelProperty(value = "题目题干", required = true)
    @Excel(name = "题目题干")
    @NotBlank(message = "题目题干不能为空")
    private String questionTitle;

    @ApiModelProperty(value = "题目答案", required = true)
    @Excel(name = "题目答案")
    @NotBlank(message = "题目答案不能为空")
    private String questionAnswer;

    @ApiModelProperty(value = "题目选项（JSON格式，如{a:'A',b:'B'}）")
    @TableField(value = "`option`", typeHandler = JacksonTypeHandler.class)
    @Excel(name = "题目选项(jason)")  // 移除处理器，直接用String接收
    private String optionStr;

    @ApiModelProperty(value = "题目解析", required = true)
    @Excel(name = "题目解析")
    @TableField("`explain`")
    @NotBlank(message = "题目解析不能为空")
    private String explain;


    @ApiModelProperty(value = "知识点id（逗号分隔，如1,2,3）")
    @Excel(name = "知识点id(数字用逗号隔开例如:1,2,3)")  // 移除处理器，直接用String接收
    private String knowledgeIdStr;  // 新增：String接收Excel内容



    public List<Map<String, Object>> getOption() {
        // 处理空字符串情况
        if (optionStr == null || optionStr.trim().isEmpty()) {
            return new ArrayList<>();
        }

        try {
            // 将 JSON 字符串解析为 List<Map<String, Object>>
            List<Map<String, Object>> optionList = JSON.parseObject(
                    optionStr.trim(),
                    new TypeReference<List<Map<String, Object>>>() {}
            );

            // 遍历处理每个 Map 中的空值
            for (Map<String, Object> optionMap : optionList) {
                // 确保 Map 中的值不为 null，空字符串保持不变
                for (Map.Entry<String, Object> entry : optionMap.entrySet()) {
                    if (entry.getValue() == null) {
                        entry.setValue("");
                    }
                }
            }

            return optionList;
        } catch (Exception e) {
            // 解析失败时返回空列表
            return new ArrayList<>();
        }
    }


    public List<Integer> getKnowledgeId() {
        if (knowledgeIdStr == null || knowledgeIdStr.trim().isEmpty()) {
            return Collections.emptyList();
        }

        List<Integer> knowledgeIds = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(knowledgeIdStr.trim(), ",");
        while (st.hasMoreTokens()) {
            String token = st.nextToken().trim();
            if (!token.isEmpty()) {
                try {
                    knowledgeIds.add(Integer.parseInt(token));
                } catch (NumberFormatException e) {
                }
            }
        }
        return knowledgeIds;
    }
}