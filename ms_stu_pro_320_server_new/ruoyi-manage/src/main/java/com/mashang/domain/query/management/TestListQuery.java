package com.mashang.domain.query.management;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.mashang.domain.model.BaseModel;
import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;


@Data
@ApiModel(value="试卷查询条件" +
        "")
public class TestListQuery {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "试卷主键")
    @TableId(value = "test_id", type = IdType.AUTO)
    private Integer testId;

    @ApiModelProperty(value = "年级")
    private Integer grade;

    @ApiModelProperty(value = "学科id")
    private Integer subjectId;


}