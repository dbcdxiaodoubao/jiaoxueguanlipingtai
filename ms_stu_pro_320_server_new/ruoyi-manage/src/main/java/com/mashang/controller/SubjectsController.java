package com.mashang.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mashang.comming.SubjectsMapping;
import com.mashang.domain.entity.Subjects;
import com.mashang.domain.query.common.PageQuery;
import com.mashang.domain.query.management.SubjectsCreat;
import com.mashang.domain.query.management.SubjectsUpdate;
import com.mashang.domain.vo.management.SubjectsDtlVo;
import com.mashang.domain.vo.management.SubjectsListVo;
import com.mashang.domain.vo.student.SubjectsListByGradeVo;
import com.mashang.service.IKnowledgeService;
import com.mashang.service.IQuestionService;
import com.mashang.service.ISubjectsService;
import com.mashang.service.ITestService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/subject")
@RestController
@Api(tags = "学科管理")
public class SubjectsController extends BaseController {

    @Autowired
    ISubjectsService subjectsService;

    @Autowired
    IKnowledgeService knowledgeService;

    @Autowired
    IQuestionService questionService;

    @Autowired
    ITestService testService;

    @GetMapping("/list")
    @ApiOperation("查询学科信息列表")
    public TableDataInfo<List<SubjectsListVo>> list(@Validated PageQuery pageQuery, Long grade){
        Page<Object> page = PageHelper.startPage(pageQuery.getPageNum(), pageQuery.getPageSize());

        List<SubjectsListVo> list = subjectsService.list(grade);

        return getDataTable(page.getResult(),page.getTotal());
    }

    @GetMapping("/dtl/{subjectsId}")
    @ApiOperation("查询学科详情")
    public R<SubjectsDtlVo> selectById(@PathVariable @Validated Long subjectsId){
        return R.ok(SubjectsMapping.INSTANCE.toDtlVo(subjectsService.getById(subjectsId)));
    }

    @GetMapping("/student/list")
    @ApiOperation("根据学生的年级查询学科列表")
    public R<List<SubjectsListByGradeVo>> listByGrade(){
        List<SubjectsListByGradeVo> subjectsListByGradeVos = subjectsService.listByGrade();
        return R.ok(subjectsListByGradeVos);
    }

    @PostMapping
    @ApiOperation("新增学科信息")
    public R insert(@RequestBody @Validated SubjectsCreat subjectsCreat){

        if(subjectsService.selectBySubjectNameGrade(subjectsCreat.getSubjectName()
                ,subjectsCreat.getGrade())!=0){
            return R.fail("该学科已经存在");
        }

        subjectsService.save(SubjectsMapping.INSTANCE.toCreate(subjectsCreat));

        return R.ok();
    }

    @PutMapping
    @ApiOperation("修改学科信息")
    public R update(@Validated @RequestBody SubjectsUpdate subjectsUpdate){

        if(subjectsService.selectBySubjectNameGrade(subjectsUpdate.getSubjectName()
                ,subjectsUpdate.getGrade())!=0){
            return R.fail("该学科已经存在");
        }

        subjectsService.updateById(SubjectsMapping.INSTANCE.toUpdate(subjectsUpdate));
        return R.ok();
    }

    @DeleteMapping("/{subjectId}")
    @ApiOperation("删除学科信息")
    public R delete(@PathVariable @Validated Integer subjectId){
        if (knowledgeService.haveKnowledege(subjectId)!=0){
            return R.fail("该学科下存在知识点，请先删除知识点再删除学科");
        }

        if (questionService.haveQuestion(subjectId)!=0){
            return R.fail("该学科下存在题目，请先删除题目再删除学科");
        }

        if (testService.haveTest(subjectId)!=0){
            return R.fail("该学科下存在试卷，请先删除试卷再删除学科");
        }

        if (subjectsService.removeById(subjectId)) {
            return R.ok();
        }
        return R.fail();
    }

}
