package com.mashang.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mashang.domain.query.common.PageQuery;
import com.mashang.domain.vo.management.StudentListVo;
import com.mashang.service.IStudentService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student")
@Api(tags = "学生管理")
public class StudentController extends BaseController {

    @Autowired
    IStudentService studentService;

    @GetMapping("/list")
    @ApiOperation("查询学生信息列表")
    public R<PageInfo<StudentListVo>> list(PageQuery pageQuery,String studentName){
        PageHelper.startPage(pageQuery.getPageNum(),pageQuery.getPageSize());

        List<StudentListVo> list = studentService.list(studentName);

        return R.ok(new  PageInfo<StudentListVo>(list));
    }

}
