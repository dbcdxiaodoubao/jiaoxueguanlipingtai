package com.mashang.controller;

import com.github.pagehelper.Page;
import com.mashang.domain.query.common.PageQuery;
import com.mashang.domain.query.student.RandomTestQuery;
import com.mashang.domain.query.student.RandomTestSubmit;
import com.mashang.domain.vo.student.RandomTestInfo;
import com.mashang.domain.vo.student.RandomTestVo;
import com.mashang.domain.vo.student.TestAnswerInfo;
import com.mashang.service.IRandomTestService;
import com.mashang.service.ITestAnswerService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/intelligence")
@Api(tags = "智能训练")
public class IntelligenceTrainingController extends BaseController {
    @Autowired
    private IRandomTestService randomTestService;
    @Autowired
    private ITestAnswerService testAnswerService;

    @PostMapping("/produce")
    @ApiOperation("自动生成随机试卷")
    public AjaxResult produceRandomTest(@Validated @RequestBody RandomTestQuery randomTestQuery){
        return toAjax(randomTestService.produceRandomTest(randomTestQuery));
    }

    @GetMapping("/list")
    @ApiOperation("智能训练生成的试卷分页查询")
    public R<Page<RandomTestVo>> listRandomTests(@Validated PageQuery pageQuery){
        Long userId = SecurityUtils.getUserId();
        Page<RandomTestVo> randomTestVos = randomTestService.listRandomTests(pageQuery, userId);
        return R.ok(randomTestVos);
    }

    @PostMapping("/create/{randomTestId}")
    @ApiOperation("创建随机答卷")
    public AjaxResult createRandomTest(@PathVariable Long randomTestId){
        return toAjax(testAnswerService.createRandomTest(randomTestId));
    }

    @GetMapping("/info/{randomTestId}")
    @ApiOperation("查询随机试卷详情")
    public R<TestAnswerInfo> getRandomInfo(@PathVariable Long randomTestId){
        TestAnswerInfo randomInfo = testAnswerService.getRandomInfo(randomTestId);
        return R.ok(randomInfo);
    }


}
