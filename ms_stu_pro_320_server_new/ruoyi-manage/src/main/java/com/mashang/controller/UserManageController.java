package com.mashang.controller;

import com.mashang.domain.query.teacher.StudentPageQuery;
import com.mashang.domain.vo.teacher.TeacherClassListVo;
import com.mashang.service.IClassService;
import com.mashang.service.IStudentService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Delete;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/teacher-user-manage")
@Api(tags = "用户管理")
@RequiredArgsConstructor
public class UserManageController {

    private final IStudentService studentService;
    private final IClassService classService;

    @GetMapping("/list")
    @ApiOperation("分页查询学生列表")
    @PreAuthorize("@ss.hasPermi('teacher:user:list')")
    public TableDataInfo list(@Validated StudentPageQuery query) {
        return studentService.pageQuery(query);
    }

    @DeleteMapping("/exitClass/{userId}")
    @ApiOperation("移除学生")
    @PreAuthorize("@ss.hasPermi('teacher:user:delete')")
    @Log(title = "移除学生", businessType = BusinessType.DELETE)
    public R<Void> exitClass(@ApiParam("移除的学生id")
                                 @NotNull(message = "学生id不能为空")
                                 @PathVariable Integer userId) {
        studentService.exitClass(userId);
        return R.ok();
    }

    @GetMapping("/teacherClassList")
    @ApiOperation("查询班级列表")
    @PreAuthorize("@ss.hasPermi('teacher:user:list')")
    public R<List<TeacherClassListVo>> teacherClassList() {
        return R.ok(classService.teacherClassList());
    }
}
