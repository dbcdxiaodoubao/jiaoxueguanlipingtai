package com.mashang.controller;

import com.mashang.domain.vo.management.ManegeData;
import com.mashang.domain.vo.management.MonthQuestion;
import com.mashang.domain.vo.management.UserActivity;
import com.mashang.service.IQuestionService;
import com.mashang.service.IStuLogininfoService;
import com.mashang.service.ITestAnswerService;
import com.mashang.service.ITestService;
import com.ruoyi.common.core.domain.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/echart")
@Api(tags = "可视化管理")
public class EChartController {

    @Autowired
    ITestService testService;

    @Autowired
    IQuestionService questionService;

    @Autowired
    ITestAnswerService testAnswerService;

    @Autowired
    IStuLogininfoService stuLogininfoService;

    @GetMapping()
    @ApiOperation("管理端方格数据")
    @PreAuthorize("@ss.hasPermi('manage:echart:manegedata')")
    public R<ManegeData> manegeData() {
        ManegeData manegeData = new ManegeData();
        manegeData.setTestSum(testService.count());
        manegeData.setQuestionSum(questionService.count());
        manegeData.setAnswerSum(testAnswerService.count());
        manegeData.setTestAnswerSum(testAnswerService.getTestAnswerCount());

        return R.ok(manegeData);
    }

    @GetMapping("/useractivity")
    @ApiOperation("用户活跃度")
    @PreAuthorize("@ss.hasPermi('manage:echart:useractivity')")
    public R<List<UserActivity>> userActivity() {
        List<UserActivity> list = stuLogininfoService.getUserActivity();
        return R.ok(list);
    }

    @GetMapping("/monthquestion")
    @ApiOperation("题目月数量")
    @PreAuthorize("@ss.hasPermi('manage:echart:monthquestion')")
    public R<List<MonthQuestion>> monthQuestion() {
        List<MonthQuestion> list = questionService.monthQuestionList();
        return R.ok(list);
    }
}
