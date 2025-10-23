package com.mashang.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mashang.domain.query.common.PageQuery;
import com.mashang.domain.vo.management.StuLoginInfoVo;
import com.mashang.domain.vo.management.StudentDtlVo;
import com.mashang.domain.vo.management.StudentListVo;
import com.mashang.service.IStuLogininfoService;
import com.mashang.service.IStudentService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student")
@Api(tags = "学生管理")
public class StudentController extends BaseController {

    @Autowired
    IStudentService studentService;

    @Autowired
    IStuLogininfoService stuLogininfoService;

    @GetMapping("/list")
    @ApiOperation("查询学生信息列表")
    public R<PageInfo<StudentListVo>> list(PageQuery pageQuery,String studentName){
        PageHelper.startPage(pageQuery.getPageNum(),pageQuery.getPageSize());

        List<StudentListVo> list = studentService.list(studentName);

        return R.ok(new  PageInfo<StudentListVo>(list));
    }

    @GetMapping("/dtl/{userId}")
    @ApiOperation("根据id查询学生详情")
    @ApiImplicitParam(value = "userId",name = "学生id")
    public R<StudentDtlVo> selectById(@PathVariable Long userId){
        return R.ok(studentService.selectByid(userId));
    }

    @GetMapping("/logininfo")
    @ApiOperation("查询学生登录日志列表")
    public R<PageInfo<StuLoginInfoVo>> stuLoginInfoList(PageQuery pageQuery, String userName){
        PageHelper.startPage(pageQuery.getPageNum(),pageQuery.getPageSize());

        List<StuLoginInfoVo> list = stuLogininfoService.list(userName);

        return R.ok(new PageInfo<StuLoginInfoVo>(list));
    }

}
