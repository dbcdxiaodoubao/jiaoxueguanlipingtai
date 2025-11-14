package com.mashang.controller;

import com.mashang.domain.vo.teacher.*;
import com.mashang.service.IClassService;
import com.mashang.service.ITeacherServicee;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/teacherHome")
@Api(tags = "教师主页")
@RequiredArgsConstructor
public class TeacherHomeController extends BaseController {

    private final ITeacherServicee teacherService;


    @GetMapping("/home/total")
    @ApiOperation("查询班级总数，班级总人数，试卷总数，题目总数")
    @PreAuthorize("@ss.hasPermi('teacher:home:list')")
    public R<TotalVo> total(){
        return R.ok(teacherService.total());
    }

    @GetMapping("/home/class-size-distribution")
    @ApiOperation("查询班级人数分布")
    @PreAuthorize("@ss.hasPermi('teacher:home:list')")
    public R<List<ClassSizeDistributionVo>> classSizeDistribution(){
        return R.ok(teacherService.classSizeDistribution());
    }

    @GetMapping("/home/class-test-distribution")
    @ApiOperation("查询班级试卷分布")
    @PreAuthorize("@ss.hasPermi('teacher:home:list')")
    public R<List<ClassTestDistributionVo>> classTestDistribution(){
        return R.ok(teacherService.classTestDistribution());
    }


}

