package com.mashang.controller;


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
import com.mashang.service.ISubjectsService;
import com.ruoyi.common.core.domain.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/subject")
@RestController
@Api(tags = "学科管理")
public class SubjectsController {

    @Autowired
    ISubjectsService subjectsService;

    @GetMapping("/list")
    @ApiOperation("查询学科信息列表")
    public R<PageInfo<SubjectsListVo>> list(@Validated PageQuery pageQuery, Long grade){
        PageHelper.startPage(pageQuery.getPageNum(), pageQuery.getPageSize());

        List<SubjectsListVo> list = subjectsService.list(grade);

        return R.ok(new PageInfo<>(list));
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
        if (subjectsService.removeById(subjectId)) {
            return R.ok();
        }
        return R.fail();
    }

}
