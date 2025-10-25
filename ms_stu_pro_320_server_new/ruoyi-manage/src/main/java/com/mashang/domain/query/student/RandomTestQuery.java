package com.mashang.domain.query.student;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RandomTestQuery {
    @ApiModelProperty("学科id")
    private Long subjectId;

    @ApiModelProperty("单选题数")
    private Integer singleNum;

    @ApiModelProperty("多选题数")
    private Integer multipleNum;

    @ApiModelProperty("判断题数")
    private Integer judgmentNum;

    @ApiModelProperty(value = "题目难度（1-10）")
    private Integer questionDifficult;
}
