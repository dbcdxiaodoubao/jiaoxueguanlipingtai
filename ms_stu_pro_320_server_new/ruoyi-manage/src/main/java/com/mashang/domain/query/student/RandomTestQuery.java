package com.mashang.domain.query.student;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RandomTestQuery {
    @ApiModelProperty(value = "学科id",required = true)
    @NotNull(message = "学科id不能为空")
    private Long subjectId;

    @ApiModelProperty("单选题数")
    private Integer singleNum;

    @ApiModelProperty("多选题数")
    private Integer multipleNum;

    @ApiModelProperty("判断题数")
    private Integer judgmentNum;

    @ApiModelProperty(value = "题目难度（1-5）",required = true)
    @NotNull(message = "题目难度不能为空")
    private Integer questionDifficult;
}
