package com.mashang.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mashang.domain.query.common.PageQuery;
import com.mashang.domain.vo.management.AdminDtlVo;
import com.mashang.domain.vo.management.AdminListVo;
import com.mashang.service.IAdminService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/admin")
@RestController
@Api(tags = "管理员管理")
public class AdminController extends BaseController {

    @Autowired
    IAdminService adminService;

    @GetMapping("/list")
    @ApiOperation("查询管理员信息列表")
    @PreAuthorize("@ss.hasPermi('manage:admin:list')")
    public TableDataInfo<List<AdminListVo>> list(@Validated PageQuery pageQuery, String nickName) {
        Page<Object> page = PageHelper.startPage(pageQuery.getPageNum(), pageQuery.getPageSize());

        List<AdminListVo> list = adminService.list(nickName);

        return getDataTable(page.getResult(),page.getTotal());
    }

    @GetMapping("/dtl/{userId}")
    @ApiOperation("查询管理员详情")
    @PreAuthorize("@ss.hasPermi('manage:admin:dtl')")
    public R<AdminDtlVo> selectById(@PathVariable @Validated Long userId) {
        return R.ok(adminService.selectById(userId));
    }
}
