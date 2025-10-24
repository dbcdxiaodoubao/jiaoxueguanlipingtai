package com.mashang.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mashang.domain.query.common.PageQuery;
import com.mashang.domain.vo.management.TeacherDtlVo;
import com.mashang.domain.vo.management.TeacherListVo;
import com.mashang.service.ITeacherServicee;
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

@RestController
@RequestMapping("/teacher")
@Api(tags = "教师管理")
public class TeacherController {

    @Autowired
    ITeacherServicee iTeacherServicee;

    @GetMapping("/list")
    @ApiOperation("查询教师信息列表")
    public R<PageInfo<TeacherListVo>> list(@Validated PageQuery pageQuery , String nickName){
        PageHelper.startPage(pageQuery.getPageNum(),pageQuery.getPageSize());

        List<TeacherListVo> list = iTeacherServicee.list(nickName);

        return R.ok(new PageInfo<TeacherListVo>(list));
    }

    @GetMapping("/dtl/{userId}")
    @ApiOperation("查询教师详情")
    public R<TeacherDtlVo> selectById(@PathVariable @Validated Long userId){
        return R.ok(iTeacherServicee.selectByid(userId));
    }

}
