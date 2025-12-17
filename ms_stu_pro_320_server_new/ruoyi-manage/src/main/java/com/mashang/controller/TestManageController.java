package com.mashang.controller;

import cn.hutool.core.util.ObjectUtil;
import com.mashang.comming.ClassMapping;
import com.mashang.comming.SubjectsMapping;
import com.mashang.comming.TestMapping;
import com.mashang.constant.StatusConstant;
import com.mashang.domain.entity.Class;
import com.mashang.domain.entity.Question;
import com.mashang.domain.entity.Subjects;
import com.mashang.domain.entity.Test;
import com.mashang.domain.param.teacher.TTestCreat;
import com.mashang.domain.param.teacher.TestUpdate;
import com.mashang.domain.query.management.QuestionTestCreat;
import com.mashang.domain.query.management.TestCreat;
import com.mashang.domain.query.teacher.TestPageQuery;
import com.mashang.domain.vo.teacher.SubjectsListVo;
import com.mashang.domain.vo.teacher.TestClassListVo;
import com.mashang.domain.vo.teacher.TestDtlVo;
import com.mashang.domain.vo.teacher.TestListVo;
import com.mashang.service.IClassService;
import com.mashang.service.IQuestionService;
import com.mashang.service.ISubjectsService;
import com.mashang.service.ITestService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("teacher-test-manage")
@Api(tags = "教师端试卷管理")
@RequiredArgsConstructor
public class TestManageController {

    private final ITestService testService;
    private final ISubjectsService subjectsService;
    private final IClassService classService;
    private final ClassMapping classMapping;
    private final SubjectsMapping subjectsMapping;
    private final TestMapping testMapping;
    private final IQuestionService questionService;

    @GetMapping("/list")
    @ApiOperation("分页查询试卷列表")
    @PreAuthorize("@ss.hasPermi('teacher:test:list')")
    public R<List<TestListVo>> list(@Validated TestPageQuery query) {
        return R.ok(testService.pageQueryTeacher(query));
    }

    @PutMapping
    @ApiOperation("修改试卷信息")
    @PreAuthorize("@ss.hasPermi('teacher:test:update')")
    @Log(title = "修改试卷信息", businessType = BusinessType.UPDATE)
    public R<Void> update(@RequestBody @Validated TestUpdate testUpdate) {
        testService.update(testUpdate);
        return R.ok();
    }

    @GetMapping("/{testId}")
    @ApiOperation("查询试卷详情")
    @PreAuthorize("@ss.hasPermi('teacher:test:query')")
    public R<TestDtlVo> getById(@NotNull(message = "试卷id不能为空")
                                     @PathVariable @ApiParam("试卷id") Integer testId) {
        return R.ok(testService.getById(testId));
    }

    @DeleteMapping("/{testId}")
    @ApiOperation("删除试卷")
    @PreAuthorize("@ss.hasPermi('teacher:test:delete')")
    @Log(title = "删除试卷", businessType = BusinessType.DELETE)
    public R<Void> delete(@NotNull(message = "试卷id不能为空")
                               @PathVariable @ApiParam("试卷id") Integer testId) {
        if (testService.haveTestAnswer(testId) > 0) {
            return R.fail("试卷下存在关联答卷无法删除");
        }
        testService.removeById(testId);
        return R.ok();
    }

    /*@PostMapping
    @ApiOperation("添加试卷")
    public R<Void> insert(@ApiParam("新增试卷信息") @RequestBody Test test, @ApiParam("绑定班级id集合") List<Integer> classIds) {
        testService.insert(test, classIds);
        return R.ok();
    }*/

    @GetMapping("/subjects")
    @ApiOperation("查询学科列表")
    @PreAuthorize("@ss.hasPermi('teacher:test:list')")
    public R<List<SubjectsListVo>> listSubjects() {
        return R.ok(subjectsMapping.toSubjectsListVo(subjectsService.lambdaQuery()
                .eq(Subjects::getGrade,SecurityUtils.getLoginUser().getUser().getGrade()).list()));
    }

    @GetMapping("/classes")
    @ApiOperation("查询班级列表")
    @PreAuthorize("@ss.hasPermi('teacher:test:list')")
    public R<List<TestClassListVo>> listClasses() {
        return R.ok(classMapping.toTestClassListVo(classService.lambdaQuery()
                .eq(Class::getTeacherId,SecurityUtils.getUserId()).list()));
    }

    @PostMapping
    @ApiOperation("新增试卷")
    @PreAuthorize("@ss.hasPermi('teacher:test:add')")
    public R<Void> insert(@RequestBody @Validated TTestCreat ttestCreat){
        if(!subjectsService.selectGradeById(ttestCreat.getSubjectId()).equals(ttestCreat.getGrade())){
            return R.fail("新增试卷的年级应和所选科目的年级一样");
        }
        if(testService.haveTestByName(ttestCreat.getTestName())!=0){
            return R.fail("该试卷名称已经存在");
        }
        if(ttestCreat.getTestType()<0||ttestCreat.getTestType()>5){
            return R.fail("试卷类型不合法，应为0-5");
        }
        for (QuestionTestCreat questionTestCreat : ttestCreat.getQuestion()) {
            if(!questionService.lambdaQuery().eq(Question::getQuestionId,questionTestCreat.getQuestionId()).exists()){
                return R.fail("所关联的题目不存在:"+questionTestCreat.getQuestionId());
            }
        }
        TestCreat testCreat=new TestCreat(ttestCreat.getTestName(),ttestCreat.getGrade(),ttestCreat.getSubjectId(),
                ttestCreat.getTestType(),ttestCreat.getSuggestDuration(),ttestCreat.getQuestion());
        Test test = testMapping.toCreat(testCreat);
        testService.save(test);
        for(QuestionTestCreat questionTestCreat: testCreat.getQuestion()){
            testService.linkTestQuestion(test.getTestId(),questionTestCreat);
        }
        //如果是班级试卷进行绑定班级
        if (testCreat.getTestType().equals(StatusConstant.EXAM_PAPER_TYPE_CLASS)){
            if (ObjectUtil.isEmpty(ttestCreat.getClassIds()))return R.fail("班级试卷班级绑定id为空");
            testService.linkTestClass(test.getTestId(),ttestCreat.getClassIds());
        }
        return R.ok();
    }
}
