package com.mashang.controller;

import com.mashang.domain.vo.student.VideoTestVo;
import com.mashang.service.ITestService;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/video")
@Api(tags = "视频课堂")
public class VideoLessonController {
    @Autowired
    private ITestService testService;

    @GetMapping("/student/test")
    @ApiOperation("查询未完成的视频试卷")
    public R<List<VideoTestVo>> getVideoTests(){
        Long userId = SecurityUtils.getUserId();
        List<VideoTestVo> videoTests = testService.getVideoTests(userId);
        return R.ok(videoTests);
    }
}
