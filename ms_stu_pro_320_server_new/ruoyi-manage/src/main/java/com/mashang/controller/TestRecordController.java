package com.mashang.controller;

import com.github.pagehelper.Page;
import com.mashang.domain.query.common.PageQuery;
import com.mashang.domain.query.student.TestPageQuery;
import com.mashang.domain.query.student.TestRecordQuery;
import com.mashang.domain.vo.student.TestRecordListVo;
import com.mashang.service.ITestAnswerService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/record")
@Api(tags = "考试记录")
public class TestRecordController extends BaseController {
    @Autowired
    private ITestAnswerService testAnswerService;

    @GetMapping("/page")
    @ApiOperation("根据提交时间倒叙分页查询该学生所有做过的试卷基本信息")
    public TableDataInfo<List<TestRecordListVo>> pageTestRecord(@Validated PageQuery pageQuery, Long subjectId){
        TestRecordQuery testRecordQuery = new TestRecordQuery();
        Long userId = SecurityUtils.getUserId();
        testRecordQuery.setUserId(userId);
        testRecordQuery.setSubjectId(subjectId);
        Page<TestRecordListVo> page = testAnswerService.listTestRecord(pageQuery, testRecordQuery);
        return getDataTable(page);
    }

}
