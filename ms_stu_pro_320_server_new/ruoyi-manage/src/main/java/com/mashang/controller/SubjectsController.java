package com.mashang.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mashang.comming.SubjectsMapping;
import com.mashang.domain.entity.Subjects;
import com.mashang.domain.query.common.PageQuery;
import com.mashang.domain.vo.management.SubjectsDtlVo;
import com.mashang.domain.vo.management.SubjectsListVo;
import com.mashang.domain.vo.student.SubjectsListByGradeVo;
import com.mashang.service.ISubjectsService;
import com.ruoyi.common.core.domain.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/subject")
@RestController
@Api(tags = "学科管理")
public class SubjectsController {

    @Autowired
    ISubjectsService subjectsService;

    @GetMapping("/list")
    @ApiOperation("查询学科信息列表")
    public R<PageInfo<SubjectsListVo>> list(PageQuery pageQuery, Long grade){
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
}
