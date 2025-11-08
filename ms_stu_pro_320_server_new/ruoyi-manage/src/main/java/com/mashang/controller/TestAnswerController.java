package com.mashang.controller;

import com.mashang.constant.StatusConstant;
import com.mashang.domain.entity.QuestionAnswer;
import com.mashang.domain.query.manage.TestAnswerPageQuery;
import com.mashang.domain.vo.management.TestAnswerDtlVo;
import com.mashang.domain.vo.management.TestAnswerListVo;
import com.mashang.domain.vo.management.TestAnswerQuestionAnswerVo;
import com.mashang.service.ITestAnswerService;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/test-answer")
@RequiredArgsConstructor
@Api(tags = "答卷管理")
public class TestAnswerController {

    private final ITestAnswerService testAnswerService;

    @GetMapping("/correctList")
    @ApiOperation("批改试卷列表查询")
    public TableDataInfo correctList(@Validated TestAnswerPageQuery pageQuery) {
        return testAnswerService.testAnswerlist(pageQuery, StatusConstant.ANSWER_STATUS_PENDING);
    }

    @GetMapping("/{testAnswerId}")
    @ApiOperation("查询试卷详情")
    public R<TestAnswerDtlVo> getTestAnswerInfo(@NotNull(message = "答卷id为空") @PathVariable Long testAnswerId) {
        return R.ok(testAnswerService.getTestAnswerInfo(testAnswerId));
    }

    /*@PutMapping("/submitAutoCorrect/{testAnswerId}")
    @ApiOperation("客观题自动批改")
    public R<Void> submitAutoCorrect(@NotNull(message = "答卷id为空") @PathVariable Long testAnswerId) {
        testAnswerService.submitAutoCorrect(testAnswerId);
        return R.ok();
    }*/

    @PutMapping("/correct")
    @ApiOperation("主观题批改")
    public R<Void> correct(@NotNull(message = "主观题列表为空") @RequestBody List<QuestionAnswer> questionAnswerList) {
        testAnswerService.correct(questionAnswerList);
        return R.ok();
    }

    @GetMapping("/list")
    @ApiOperation("分页查询答卷列表")
    public TableDataInfo list(@Validated TestAnswerPageQuery pageQuery) {
        return testAnswerService.testAnswerlist(pageQuery,null);
    }

}
