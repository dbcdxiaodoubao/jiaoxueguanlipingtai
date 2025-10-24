package com.mashang.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mashang.domain.query.common.PageQuery;
import com.mashang.domain.vo.management.AdminDtlVo;
import com.mashang.domain.vo.management.AdminListVo;
import com.mashang.service.IAdminService;
import com.ruoyi.common.core.domain.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/admin")
@RestController
@Api(tags = "管理员管理")
public class AdminController {

    @Autowired
    IAdminService adminService;

    @GetMapping("/list")
    @ApiOperation("查询管理员信息列表")
    public R<PageInfo<AdminListVo>> list(@Validated PageQuery pageQuery, String nickName) {
        PageHelper.startPage(pageQuery.getPageNum(), pageQuery.getPageSize());

        List<AdminListVo> list = adminService.list(nickName);

        return R.ok(new PageInfo<>(list));
    }

    @GetMapping("/dtl/{userId}")
    @ApiOperation("查询管理员详情")
    public R<AdminDtlVo> selectById(@PathVariable @Validated Long userId) {
        return R.ok(adminService.selectById(userId));
    }
}
