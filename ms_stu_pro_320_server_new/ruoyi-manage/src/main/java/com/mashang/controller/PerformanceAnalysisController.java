package com.mashang.controller;

import cn.hutool.core.util.ObjectUtil;
import com.mashang.domain.entity.Class;
import com.mashang.domain.vo.teacher.StudentAverageVo;
import com.mashang.domain.vo.teacher.TestAverageVo;
import com.mashang.service.IClassService;
import com.mashang.service.ITeacherServicee;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/analysis")
@Api(tags = "成绩分析")
@RequiredArgsConstructor
public class PerformanceAnalysisController {

    private final ITeacherServicee teacherService;
    private final IClassService classService;

    @GetMapping("/class-average")
    @ApiOperation("查询班级平均分")
    @PreAuthorize("@ss.hasPermi('teacher:analysis:list')")
    public R<List<TestAverageVo>> testAverage(){
        return R.ok(teacherService.testAverage());
    }

    @GetMapping("/student-average/{classId}")
    @ApiOperation("查询班级下学生的成绩")
    @PreAuthorize("@ss.hasPermi('teacher:analysis:list')")
    public R<List<StudentAverageVo>> studentAverage(@PathVariable @NotNull(message = "班级id不能为空")
                                                    @ApiParam("班级id") Integer classId){
        Class aClass = classService.lambdaQuery().eq(Class::getClassId, classId).one();
        if(ObjectUtil.isNull(aClass))return R.fail("查找班级不存在");
        Long teacherId = aClass.getTeacherId();
        if (!teacherId.equals(SecurityUtils.getUserId())){
            return R.fail("查询班级不属于当前教师");
        }
        return R.ok(teacherService.studentAverage(classId));
    }
}
