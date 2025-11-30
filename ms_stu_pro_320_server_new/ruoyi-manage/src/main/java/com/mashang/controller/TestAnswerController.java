package com.mashang.controller;

import com.mashang.comming.ClassMapping;
import com.mashang.constant.StatusConstant;
import com.mashang.domain.entity.Class;
import com.mashang.domain.entity.QuestionAnswer;
import com.mashang.domain.param.teacher.TestCorrectParam;
import com.mashang.domain.query.manage.TestAnswerPageQuery;
import com.mashang.domain.vo.management.TestAnswerDtlVo;
import com.mashang.domain.vo.management.TestAnswerListVo;
import com.mashang.domain.vo.management.TestAnswerQuestionAnswerVo;
import com.mashang.domain.vo.teacher.TAClassListVo;
import com.mashang.service.IClassService;
import com.mashang.service.ITestAnswerService;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private final IClassService classService;
    private final ClassMapping classMapping;

    @GetMapping("/correctList")
    @ApiOperation("批改试卷列表查询")
    @PreAuthorize("@ss.hasPermi('teacher:tsetanswer:correctlist')")
    public TableDataInfo correctList(@Validated TestAnswerPageQuery pageQuery) {
        return testAnswerService.testAnswerlist(pageQuery, StatusConstant.ANSWER_STATUS_PENDING);
    }

    @GetMapping("/{testAnswerId}")
    @ApiOperation("查询试卷详情")
    @PreAuthorize("@ss.hasPermi('teacher:tsetanswer:dtl')")
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
    @PreAuthorize("@ss.hasPermi('teacher:tsetanswer:correct')")
    public R<Void> correct(@RequestBody TestCorrectParam testCorrectParam) {
        testAnswerService.correct(testCorrectParam);
        return R.ok();
    }

    @GetMapping("/list")
    @ApiOperation("分页查询答卷列表")
    @PreAuthorize("@ss.hasPermi('teacher:tsetanswer:list')")
    public TableDataInfo list(@Validated TestAnswerPageQuery pageQuery) {
        return testAnswerService.testAnswerlist(pageQuery,null);
    }

    @GetMapping("/classList")
    @ApiOperation("班级列表")
    @PreAuthorize("@ss.hasPermi('teacher:tsetanswer:list')")
    public R<List<TAClassListVo>> classList() {
        List<Class> list = classService.lambdaQuery().eq(Class::getGrade, SecurityUtils.getLoginUser().getUser().getGrade()).list();
        return R.ok(classMapping.toTAClassListVo(list));
    }

}
