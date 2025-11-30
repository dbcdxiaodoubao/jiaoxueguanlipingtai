package com.mashang.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mashang.comming.ClassMapping;
import com.mashang.domain.entity.Class;
import com.mashang.domain.param.teacher.ClassInsert;
import com.mashang.domain.param.teacher.ClassUpdate;
import com.mashang.domain.query.teacher.ClassPageQuery;
import com.mashang.domain.vo.teacher.ClassDtlVo;
import com.mashang.service.IClassService;
import com.mashang.service.IStudentService;
import com.mashang.service.ITeacherServicee;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/teacher-class-manage")
@Api(tags = "班级管理")
@RequiredArgsConstructor
public class ClassManageController {

    private final IClassService classService;
    private final ClassMapping classMapping;
    private final IStudentService studentService;
    private final ITeacherServicee teacherService;

    @GetMapping("/list")
    @ApiOperation("分页查询班级列表")
    @PreAuthorize("@ss.hasPermi('teacher:class:list')")
    public TableDataInfo list(@Validated ClassPageQuery query) {
        Page<Class> page =new Page<>(query.getPageNum(),query.getPageSize());
        classService.page(page,new LambdaQueryWrapper<Class>()
                        .eq(Class::getTeacherId,SecurityUtils.getUserId())
                .like(StringUtils.isNotEmpty(query.getClassName()),Class::getClassName,query.getClassName())
                .orderByDesc(Class::getCreateTime));
        return new TableDataInfo(classMapping.toClassListVo(page.getRecords()),page.getTotal());
    }

    @GetMapping("/{classId}")
    @ApiOperation("查询班级详情")
    @PreAuthorize("@ss.hasPermi('teacher:class:query')")
    public R<ClassDtlVo> selectById(@PathVariable @Validated Integer classId){
        Class aClass = classService.getById(classId);
        return R.ok(classMapping.toClassDtlVo(aClass));
    }

    @PutMapping()
    @ApiOperation("修改班级信息")
    @PreAuthorize("@ss.hasPermi('teacher:class:update')")
    @Log(title = "修改班级信息", businessType = BusinessType.UPDATE)
    public R<Void> update(@RequestBody @Validated ClassUpdate classUpdate){
        Class aClass = classService.lambdaQuery().eq(Class::getClassName, classUpdate.getClassName())
                .ne(Class::getClassId, classUpdate.getClassId()).one();
        if(ObjectUtil.isNotNull(aClass))return R.fail("班级名称已存在");
        return R.result(classService.updateById(new Class().setClassId(classUpdate.getClassId())
                .setClassName(classUpdate.getClassName())));
    }

    @DeleteMapping("/{classId}")
    @ApiOperation("删除班级")
    @PreAuthorize("@ss.hasPermi('teacher:class:delete')")
    @Log(title = "删除班级", businessType = BusinessType.DELETE)
    public R<Void> delete(@NotNull(message = "班级id为空") @ApiParam("班级id") @PathVariable Integer classId){
        //检查该班级下是否存在学生

        if (studentService.lambdaQuery().select(SysUser::getUserId)
                .eq(SysUser::getClassId, classId)
                .ne(SysUser::getUserId, SecurityUtils.getUserId()).exists())return R.fail("该班级下存在学生，请先移除学生");

        /*//解除教师与该班级的绑定
        teacherService.lambdaUpdate().eq(SysUser::getClassId, classId)
                .eq(SysUser::getUserId,SecurityUtils.getUserId())
                .set(SysUser::getClassId,null).update();*/
        return R.result(classService.removeById(classId));
    }

    @PostMapping
    @ApiOperation("添加班级")
    @PreAuthorize("@ss.hasPermi('teacher:class:add')")
    @Log(title = "添加班级", businessType = BusinessType.INSERT)
    public R<Void> add(@RequestBody @Validated ClassInsert classInsert){
        String className = classInsert.getClassName();
        Class aClass = classService.lambdaQuery().eq(Class::getClassName, className).one();
        if(ObjectUtil.isNotNull(aClass))return R.fail("班级名称已存在");
        return R.result(classService.save(new Class().setClassName(className)
                .setTeacherId(SecurityUtils.getUserId())
                .setClassPassword(UUID.randomUUID().toString())
                .setGrade(SecurityUtils.getLoginUser().getUser().getGrade().intValue())));
    }
}
