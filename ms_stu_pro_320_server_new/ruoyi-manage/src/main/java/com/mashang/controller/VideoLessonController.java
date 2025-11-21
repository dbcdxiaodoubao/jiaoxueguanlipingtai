package com.mashang.controller;

import com.github.pagehelper.Page;
import com.mashang.domain.query.common.PageQuery;
import com.mashang.domain.query.student.VideoTestPageQuery;
import com.mashang.domain.vo.student.VideoTestVo;
import com.mashang.service.ITestAnswerService;
import com.mashang.service.ITestService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/video")
@Api(tags = "视频课堂")
@Validated
public class VideoLessonController extends BaseController {
    @Autowired
    private ITestService testService;
    @Autowired
    private ITestAnswerService testAnswerService;

    @GetMapping("/student/test")
    @ApiOperation("查询未完成的视频答卷信息列表")
    @PreAuthorize("@ss.hasPermi('student:video:test')")
    public R<List<VideoTestVo>> getVideoTests(){
        Long userId = SecurityUtils.getUserId();
        List<VideoTestVo> videoTests = testAnswerService.getVideoTests(userId);
        return R.ok(videoTests);
    }

    @GetMapping("/student/page")
    @PreAuthorize("@ss.hasPermi('student:video:page')")
        @ApiOperation("根据条件分页查询对应的视频和关联的答卷信息")
    @ApiImplicitParam(name = "subjectId",value = "学科id",required = true)
    public TableDataInfo<List<VideoTestVo>> pageVideoTests(@Validated PageQuery pageQuery,@NotNull(message = "学科id不能为空") Long subjectId){
        VideoTestPageQuery videoTestPageQuery = new VideoTestPageQuery();
        Long userId = SecurityUtils.getUserId();
        videoTestPageQuery.setUserId(userId);
        videoTestPageQuery.setSubjectId(subjectId);
        Page<VideoTestVo> videoTestVoPage = testService.pageVideoTests(pageQuery, videoTestPageQuery);
        return getDataTable(videoTestVoPage);
    }
}
