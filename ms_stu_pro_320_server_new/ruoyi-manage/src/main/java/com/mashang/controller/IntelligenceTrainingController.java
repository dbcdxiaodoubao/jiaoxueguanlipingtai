package com.mashang.controller;

import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.github.pagehelper.Page;
import com.mashang.domain.query.common.PageQuery;
import com.mashang.domain.query.student.RandomTestQuery;
import com.mashang.domain.vo.student.RandomTestVo;
import com.mashang.domain.vo.student.TestAnswerInfo;
import com.mashang.service.IRandomTestService;
import com.mashang.service.ITestAnswerService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/intelligence")
@Api(tags = "智能训练")
public class IntelligenceTrainingController extends BaseController {
    @Autowired
    private IRandomTestService randomTestService;
    @Autowired
    private ITestAnswerService testAnswerService;

    @PostMapping("/produce")
    @ApiOperation(value = "自动生成随机试卷")
    @PreAuthorize("@ss.hasPermi('student:random:create')")
    public AjaxResult produceRandomTest(@Validated @RequestBody RandomTestQuery randomTestQuery){
        return toAjax(randomTestService.produceRandomTest(randomTestQuery));
    }

    @GetMapping("/list")
    @ApiOperation(value = "智能训练生成的试卷分页查询",notes = "在这边查看各个随机试卷的id")
    @PreAuthorize("@ss.hasPermi('student:random:list')")
    public TableDataInfo<List<RandomTestVo>> listRandomTests(@Validated PageQuery pageQuery){
        Long userId = SecurityUtils.getUserId();
        Page<RandomTestVo> randomTestVos = randomTestService.listRandomTests(pageQuery, userId);
        return getDataTable(randomTestVos);
    }

    @PostMapping("/create/{randomTestId}")
    @ApiOperation(value = "创建随机答卷",notes = "需要在随机试卷已经存在的基础上创建答卷")
    @PreAuthorize("@ss.hasPermi('student:random:create')")
    @ApiImplicitParam(name = "randomTestId",value = "随机试卷id",required = true)
    public AjaxResult createRandomTest(@PathVariable Long randomTestId){
        return toAjax(testAnswerService.createRandomTest(randomTestId));
    }

    @GetMapping("/info/{randomTestId}")
    @ApiOperation(value = "开始答题（查看随机答卷详情）",notes = "注意只能查看刚刚创建的随机答卷详情")
    @PreAuthorize("@ss.hasPermi('student:random:info')")
    @ApiImplicitParam(name = "randomTestId",value = "随机试卷id",required = true)
    public R<TestAnswerInfo> getRandomInfo(@PathVariable Long randomTestId){
        TestAnswerInfo randomInfo = testAnswerService.getRandomInfo(randomTestId);
        return R.ok(randomInfo);
    }


}
