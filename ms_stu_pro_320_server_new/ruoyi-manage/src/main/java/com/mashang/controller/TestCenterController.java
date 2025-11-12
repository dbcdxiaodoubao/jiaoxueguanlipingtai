package com.mashang.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mashang.comming.TestAnswerMapping;
import com.mashang.comming.TestMapping;
import com.mashang.domain.entity.Test;
import com.mashang.domain.query.common.PageQuery;
import com.mashang.domain.query.management.QuestionTestCreat;
import com.mashang.domain.query.management.TestCreat;
import com.mashang.domain.query.management.TestListQuery;
import com.mashang.domain.query.management.TestUpdate;
import com.mashang.domain.query.student.TestPageQuery;
import com.mashang.domain.query.student.TestSubmit;
import com.mashang.domain.query.student.TestSubmitQuery;
import com.mashang.domain.vo.management.ManageTestListVo;
import com.mashang.domain.vo.management.TestDtlVo;
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
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @ApiOperation("查询属于当前学生的未完成的答卷（固定，时段，班级答卷）")
    @PreAuthorize("@ss.hasPermi('student:test:list')")
    public R<List<TestListVo>> getStudentTests(){
        Long userId = SecurityUtils.getUserId();
        List<TestListVo> studentTests = testAnswerService.getStudentTests(userId);
        return R.ok(studentTests);
    }

    @GetMapping("/student/info/{testAnswerId}")
    @ApiOperation("查询答卷查询详情信息")
    @PreAuthorize("@ss.hasPermi('student:test:info')")
    public R<TestAnswerInfo> getStudentTestInfo( @PathVariable Long testAnswerId){
        TestAnswerInfo studentTestInfo = testAnswerService.getStudentTestInfo(testAnswerId);
        return R.ok(studentTestInfo);
    }

    @PutMapping("/student/submit")
    @ApiOperation("提交试卷")
    @PreAuthorize("@ss.hasPermi('student:test:submit')")
    public AjaxResult submitTest(@RequestBody TestSubmitQuery testSubmitQuery){
        TestSubmit testSubmit = TestAnswerMapping.INSTANCE.toTestSubmit(testSubmitQuery);
        return toAjax(testAnswerService.submitTest(testSubmit));
    }

    @GetMapping("/student/page")
    @ApiOperation("根据条件分页查询答卷列表（固定，时段，班级答卷）")
    @PreAuthorize("@ss.hasPermi('student:test:list')")
    public TableDataInfo<List<TestListVo>> pageStudentTests(@Validated PageQuery pageQuery,@Validated TestPageQuery testPageQuery){

        Long userId = SecurityUtils.getUserId();
        Page<TestListVo> testListVos = testService.pageStudentTests(pageQuery, testPageQuery,userId);
        return getDataTable(testListVos);
    }

//    @GetMapping
//    @ApiOperation("管理端查询试卷列表")
//    public TableDataInfo<List<ManageTestListVo>> list(@Validated PageQuery pageQuery
//            ,TestListQuery testListQuery){
//        Page<Object> page = PageHelper.startPage(pageQuery.getPageNum(), pageQuery.getPageSize());
//
//        List<ManageTestListVo> list = testService.list(testListQuery);
//
//        return getDataTable(page.getResult(),page.getTotal());
//    }
//
//
//    @GetMapping("/dtl/{testId}")
//    @ApiOperation("查询试卷详情")
//    public R<TestDtlVo> dtl(@PathVariable @Validated Integer testId){
//        return R.ok(testService.dtl(testId));
//    }
//
//    @PostMapping
//    @ApiOperation("新增试卷")
//    public R insert(@RequestBody @Validated TestCreat testCreat){
//        Test test = TestMapping.INSTANCE.toCreat(testCreat);
//        testService.save(test);
//        for(QuestionTestCreat questionTestCreat: testCreat.getQuestion()){
//            testService.linkTestQuestion(test.getTestId(),questionTestCreat);
//        }
//        return R.ok();
//    }
//
//    @PutMapping
//    @ApiOperation("修改试卷")
//    public R update(TestUpdate testUpdate){
//        testService.updateById(TestMapping.INSTANCE.toUpdate(testUpdate));
//        testService.breakTestQuestion(testUpdate.getTestId());
//        for(QuestionTestCreat questionTestCreat: testUpdate.getQuestion()){
//            testService.linkTestQuestion(testUpdate.getTestId(),questionTestCreat);
//        }
//        return R.ok();
//    }
//
//    @DeleteMapping
//    @ApiOperation("删除试卷")
//    public R delete(Integer testId){
//        if (testService.haveTestAnswer(testId) != 0){
//            return R.fail("该试卷下存在答案请删除答案再删除试卷");
//        }
//        testService.removeById(testId);
//        return R.ok();
//    }
}
