package com.mashang.controller;

import com.mashang.domain.query.student.RandomTestQuery;
import com.mashang.service.IRandomTestService;
import com.mashang.service.ITestService;
import com.ruoyi.common.core.domain.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/intelligence")
@Api(tags = "智能训练")
public class IntelligenceTrainingController {
    @Autowired
    private IRandomTestService randomTestService;

    @PostMapping("/produce")
    @ApiOperation("自动生成随机试卷")
    public AjaxResult produceRandomTest(@RequestBody RandomTestQuery randomTestQuery){
        return AjaxResult.success(randomTestService.produceRandomTest(randomTestQuery));
    }
}
