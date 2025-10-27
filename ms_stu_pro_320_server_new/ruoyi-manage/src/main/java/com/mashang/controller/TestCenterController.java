package com.mashang.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mashang.domain.query.common.PageQuery;
import com.mashang.domain.query.management.TestListQuery;
import com.mashang.domain.query.student.TestPageQuery;
import com.mashang.domain.query.student.TestSubmit;
import com.mashang.domain.vo.management.ManageTestListVo;
import com.mashang.domain.vo.student.TestAnswerInfo;
import com.mashang.domain.vo.student.TestListVo;
import com.mashang.service.ITestAnswerService;
import com.mashang.service.ITestService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test")
@Api(tags = "试卷中心")
public class TestCenterController extends BaseController {
    @Autowired
    private ITestService testService;
    @Autowired
    private ITestAnswerService testAnswerService;

    @GetMapping("/student/list")
    @ApiOperation("查询属于当前学生的所有未完成的答卷")
    public R<List<TestListVo>> getStudentTests(){
        Long userId = SecurityUtils.getUserId();
        List<TestListVo> studentTests = testAnswerService.getStudentTests(userId);
        return R.ok(studentTests);
    }

    @GetMapping("/student/info/{id}")
    @ApiOperation("查询试卷详情信息")
    public R<TestAnswerInfo> getStudentTestInfo(@PathVariable Long id){
        TestAnswerInfo studentTestInfo = testAnswerService.getStudentTestInfo(id);
        return R.ok(studentTestInfo);
    }

    @PutMapping("/student/submit")
    @ApiOperation("提交试卷")
    public AjaxResult submitTest(@Validated @RequestBody TestSubmit testSubmit){
        return toAjax(testAnswerService.submitTest(testSubmit));
    }

    @GetMapping("/student/page")
    @ApiOperation("根据条件分页查询试卷列表")
    public TableDataInfo pageStudentTests(@Validated PageQuery pageQuery,@Validated TestPageQuery testPageQuery){
        testPageQuery.setUserId(SecurityUtils.getUserId());
        Page<TestListVo> testListVos = testService.pageStudentTests(pageQuery, testPageQuery);
        return getDataTable(testListVos);
    }

    @GetMapping
    @ApiOperation("管理端查询试卷列表")
    public R<PageInfo<ManageTestListVo>> list(@Validated PageQuery pageQuery
            ,TestListQuery testListQuery){
        PageHelper.startPage(pageQuery.getPageNum(), pageQuery.getPageSize());

        List<ManageTestListVo> list = testService.list(testListQuery);

        return R.ok(PageInfo.of(list));
    }
    
}
