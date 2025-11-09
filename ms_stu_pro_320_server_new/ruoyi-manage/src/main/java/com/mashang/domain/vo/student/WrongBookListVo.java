package com.mashang.domain.vo.student;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.mashang.domain.model.Option;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class WrongBookListVo {

    @ApiModelProperty(value = "答题id")
    private Integer questionAnswerId;

    @ApiModelProperty(value = "题目类型")
    private Integer questionType;

    @ApiModelProperty("学科名称")
    private String subjectName;

    @ApiModelProperty(value = "题目题干")
    private String questionTitle;

    @ApiModelProperty(value = "题目难度")
    private Integer questionDifficulty;

    @ApiModelProperty(value = "用户答案")
    private String userAnswer;

    @ApiModelProperty(value = "题目答案")
    private String rightAnswer;

    @ApiModelProperty(value = "题目分数")
    private Integer questionScore;

    @ApiModelProperty(value = "题目解析")
    @TableField("`explain`")
    private String explain;

    @ApiModelProperty(value = "题目选项")
    @TableField(value = "`option`",typeHandler = JacksonTypeHandler.class)
    private List<Option> option;
}
