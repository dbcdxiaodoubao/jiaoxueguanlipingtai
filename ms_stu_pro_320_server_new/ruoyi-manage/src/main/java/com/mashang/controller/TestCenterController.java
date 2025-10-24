package com.mashang.controller;

import com.mashang.domain.query.student.TestSubmit;
import com.mashang.domain.vo.student.TestAnswerInfo;
import com.mashang.domain.vo.student.TestListVo;
import com.mashang.domain.vo.student.VideoTestVo;
import com.mashang.service.ITestService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test")
@Api(tags = "试卷中心")
public class TestCenterController extends BaseController {
    @Autowired
    private ITestService testService;

    @GetMapping("/student/list")
    @ApiOperation("查询属于当前学生的所有未完成的答卷")
    public R<List<TestListVo>> getStudentTests(){
        Long userId = SecurityUtils.getUserId();
        List<TestListVo> studentTests = testService.getStudentTests(userId);
        return R.ok(studentTests);
    }

    @GetMapping("/student/info/{id}")
    @ApiOperation("查询试卷详情信息")
    public R<TestAnswerInfo> getStudentTestInfo(@PathVariable Long id){
        TestAnswerInfo studentTestInfo = testService.getStudentTestInfo(id);
        return R.ok(studentTestInfo);
    }

    @PutMapping("/student/submit")
    @ApiOperation("提交试卷")
    public AjaxResult submitTest(@RequestBody TestSubmit testSubmit){
        return toAjax(testService.submitTest(testSubmit));
    }

}
