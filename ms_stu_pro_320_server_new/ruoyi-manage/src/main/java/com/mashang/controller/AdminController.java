package com.mashang.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mashang.comming.UserMapping;
import com.mashang.domain.query.common.PageQuery;
import com.mashang.domain.query.management.UserCreatQuery;
import com.mashang.domain.vo.management.AdminDtlVo;
import com.mashang.domain.vo.management.AdminListVo;
import com.mashang.domain.vo.management.UserUpdateQuery;
import com.mashang.service.IAdminService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.ISysPostService;
import com.ruoyi.system.service.ISysRoleService;
import com.ruoyi.system.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.ArrayUtils;
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

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysDeptService deptService;

    @Autowired
    private ISysPostService postService;

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

    @ApiOperation("新增用户")
    @PreAuthorize("@ss.hasPermi('system:user:add')")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody UserCreatQuery tempUser)
    {

        SysUser user = UserMapping.INSTANCE.toSysUser(tempUser);

        Long [] RoleIds = {user.getRoleId()};

        user.setRoleIds(RoleIds);

        if (!userService.checkUserNameUnique(user))
        {
            return error("新增用户'" + user.getUserName() + "'失败，登录账号已存在");
        }
        else if (StringUtils.isNotEmpty(user.getPhonenumber()) && !userService.checkPhoneUnique(user))
        {
            return error("新增用户'" + user.getUserName() + "'失败，手机号码已存在");
        }
        else if (StringUtils.isNotEmpty(user.getEmail()) && !userService.checkEmailUnique(user))
        {
            return error("新增用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }
        user.setCreateBy(getUsername());
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        return toAjax(userService.insertUser(user));
    }

    @ApiOperation("删除用户")
    @PreAuthorize("@ss.hasPermi('system:user:remove')")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{userId}")
    public AjaxResult remove(@PathVariable Long userId)
    {

        Long [] userIds = {userId};

        if (ArrayUtils.contains(userIds, getUserId()))
        {
            return error("当前用户不能删除");
        }
        return toAjax(userService.deleteUserByIds(userIds));
    }

    @ApiOperation("修改用户信息")
    @PreAuthorize("@ss.hasPermi('system:user:edit')")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public R edit(@Validated @RequestBody UserUpdateQuery userUpdateQuery)
    {
        SysUser user = UserMapping.INSTANCE.toUpdate(userUpdateQuery);

        Long [] RoleIds = {user.getRoleId()};

        user.setRoleIds(RoleIds);

        userService.checkUserAllowed(user);
        userService.checkUserDataScope(user.getUserId());
        roleService.checkRoleDataScope(user.getRoleIds());
        if(user.getUserId()==null || user.getUserId()==0){
            return R.fail("用户id不能为空");
        }
        user.setUpdateBy(getUsername());
        userService.updateUser(user);
        return R.ok();
    }

    @ApiOperation("封禁用户")
    @PutMapping("/ban/{userId}")
    public R ban(@PathVariable Long userId) {
        if (userId == null || userId == 0) {
            return R.fail("用户id不能为空");
        }
        SysUser sysUser = userService.selectUserById(userId);
        if (sysUser == null) {
            return R.fail("该用户不存在");
        }

        adminService.banById(userId);

        return R.ok();
    }

    @ApiOperation("解禁用户")
    @PutMapping("/noBan/{userId}")
    public R noBan(@PathVariable Long userId) {
        if (userId == null || userId == 0) {
            return R.fail("用户id不能为空");
        }
        SysUser sysUser = userService.selectUserById(userId);
        if (sysUser == null) {
            return R.fail("该用户不存在");
        }

        adminService.noBanById(userId);

        return R.ok();
    }
}
