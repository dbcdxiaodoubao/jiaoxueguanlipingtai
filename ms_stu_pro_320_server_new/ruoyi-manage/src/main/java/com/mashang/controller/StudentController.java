package com.mashang.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mashang.domain.entity.Class;
import com.mashang.domain.query.common.PageQuery;
import com.mashang.domain.vo.management.StuLoginInfoVo;
import com.mashang.domain.vo.management.StudentDtlVo;
import com.mashang.domain.vo.management.StudentListVo;
import com.mashang.domain.vo.student.LoginInfoVo;
import com.mashang.domain.vo.student.StudentInfoVo;
import com.mashang.service.IClassService;
import com.mashang.service.IStuLogininfoService;
import com.mashang.service.IStudentService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/student")
@Api(tags = "学生管理")
public class StudentController extends BaseController {

    @Autowired
    IStudentService studentService;

    @Autowired
    IStuLogininfoService stuLogininfoService;

    @Autowired
    IClassService classService;

    @GetMapping("/list")
    @ApiOperation("查询学生信息列表")
    @PreAuthorize("@ss.hasPermi('manage:student:list')")
    public TableDataInfo<List<StudentListVo>> list(@Validated PageQuery pageQuery, String nickName){
        Page<Object> page = PageHelper.startPage(pageQuery.getPageNum(), pageQuery.getPageSize());

        List<StudentListVo> list = studentService.list(nickName);

        return getDataTable(page.getResult(),page.getTotal());
    }

    @GetMapping("/dtl/{userId}")
    @ApiOperation("根据id查询学生详情")
    @PreAuthorize("@ss.hasPermi('manage:student:dtl')")
    public R<StudentDtlVo> selectById(@PathVariable @Validated Long userId){
        return R.ok(studentService.selectByid(userId));
    }

//    @GetMapping("/logininfo")
//    @ApiOperation("查询学生登录日志列表")
//    @PreAuthorize("@ss.hasPermi('manage:student:logininfo')")
//    public TableDataInfo<List<StuLoginInfoVo>> stuLoginInfoList(@Validated PageQuery pageQuery, String userName){
//        Page<Object> page = PageHelper.startPage(pageQuery.getPageNum(), pageQuery.getPageSize());
//
//        List<StuLoginInfoVo> list = stuLogininfoService.list(userName);
//
//        return getDataTable(page.getResult(),page.getTotal());
//    }
//
//    @GetMapping("/info")
//    @ApiOperation("查询个人信息")
//    @PreAuthorize("@ss.hasPermi('student:user:info')")
//    public R<StudentInfoVo> info(){
//        return R.ok(studentService.info(SecurityUtils.getUserId()));
//    }
//
//    @PutMapping("joinClass/{classPassword}")
//    @ApiOperation("输入口令加入班级")
//    @PreAuthorize("@ss.hasPermi('student:user:joinclass')")
//    public R<Void> joinClass(@ApiParam("班级口令") @PathVariable @NotNull(message = "班级口令不能为空") String classPassword){
//        SysUser student = studentService.getById(SecurityUtils.getUserId());
//        Class aClass = classService.lambdaQuery().eq(Class::getClassPassword, classPassword).one();
//        if (aClass == null){
//            return R.fail("口令不存在");
//        }
//        if (!aClass.getGrade().equals(student.getGrade().intValue())){
//            return R.fail("学生年级与加入班级年级不一致");
//        }
//        studentService.joinClass(aClass.getClassId());
//        return R.ok();
//    }
//
//    @GetMapping("/loginInfo")
//    @ApiOperation("获取用户登录日志")
//    @PreAuthorize("@ss.hasPermi('student:user:loginInfo')")
//    private R<LoginInfoVo> loginInfo(){
//        return R.ok(studentService.loginInfo());
//    }

}
