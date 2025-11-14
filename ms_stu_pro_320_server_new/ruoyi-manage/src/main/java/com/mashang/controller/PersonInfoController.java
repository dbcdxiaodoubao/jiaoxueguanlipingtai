package com.mashang.controller;


import com.mashang.domain.entity.Class;
import com.mashang.domain.vo.student.LoginInfoVo;
import com.mashang.domain.vo.student.StudentInfoVo;
import com.mashang.service.IClassService;
import com.mashang.service.IStudentService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/personInfo")
@Api(tags = "个人信息")
public class PersonInfoController extends BaseController {

    @Autowired
    IStudentService studentService;

    @Autowired
    IClassService classService;


    @GetMapping("/info")
    @ApiOperation("查询个人信息")
    @PreAuthorize("@ss.hasPermi('student:personinfo:info')")
    public R<StudentInfoVo> info(){
        return R.ok(studentService.info(SecurityUtils.getUserId()));
    }

    @PutMapping("joinClass/{classPassword}")
    @ApiOperation("输入口令加入班级")
    @PreAuthorize("@ss.hasPermi('student:personinfo:joinclass')")
    public R<Void> joinClass(@ApiParam("班级口令") @PathVariable @NotNull(message = "班级口令不能为空") String classPassword){
        SysUser student = studentService.getById(SecurityUtils.getUserId());
        Class aClass = classService.lambdaQuery().eq(Class::getClassPassword, classPassword).one();
        if (aClass == null){
            return R.fail("口令不存在");
        }
        if (!aClass.getGrade().equals(student.getGrade().intValue())){
            return R.fail("学生年级与加入班级年级不一致");
        }
        studentService.joinClass(aClass.getClassId());
        return R.ok();
    }

    @GetMapping("/loginInfo")
    @ApiOperation("获取用户登录日志")
    @PreAuthorize("@ss.hasPermi('student:personinfo:logininfo')")
    private R<LoginInfoVo> loginInfo(){
        return R.ok(studentService.loginInfo());
    }

}
