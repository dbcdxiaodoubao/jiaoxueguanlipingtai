package com.mashang.domain.vo.student;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class RandomTestVo {
    /**
     * 随机试卷id
     */
    @ApiModelProperty("随机试卷id")
    private Long randomTestId;

    /**
     * 试卷名称
     */
    @ApiModelProperty("试卷名称")
    private String testName;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    /**
     * 学科名称
     */
    @ApiModelProperty("学科名称")
    private String subjectName;
}
