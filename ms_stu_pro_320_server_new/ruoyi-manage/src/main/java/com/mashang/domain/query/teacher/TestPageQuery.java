package com.mashang.domain.query.teacher;

import com.mashang.domain.query.common.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@ApiModel("教师端试卷列表分页查询")
@Alias("TestPageQueryTeacher")
public class TestPageQuery extends PageQuery {

    @ApiModelProperty(value = "试卷主键")
    private Integer testId;

    @ApiModelProperty(value = "学科id")
    private Integer subjectId;
}
