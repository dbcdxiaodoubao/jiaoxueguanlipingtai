package com.mashang.controller;

import com.mashang.domain.entity.Video;
import com.mashang.domain.query.manage.VideoPageQuery;
import com.mashang.domain.vo.management.VideoVo;
import com.mashang.service.IVideoService;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/video")
@RequiredArgsConstructor
@Api(tags = "视频管理")
public class VideoManageController {

    private final IVideoService videoService;

    @GetMapping("/list")
    @ApiOperation("视频列表")
    @PreAuthorize("@ss.hasPermi('teacher:video:list')")
    public TableDataInfo list(@Validated VideoPageQuery query) {
        return videoService.pageQuery(query);
    }

    @GetMapping("/{videoId}")
    @ApiOperation("查询视频详情")
    @PreAuthorize("@ss.hasPermi('teacher:video:dtl')")
    public R<VideoVo> query(@ApiParam("视频id")
                           @NotNull(message = "视频id为空")
                           @PathVariable Integer videoId){
        return R.ok(videoService.queryById(videoId));
    }

    @PostMapping
    @ApiOperation("添加视频")
    @PreAuthorize("@ss.hasPermi('teacher:video:insert')")
    public R<Void> add(@RequestBody Video video){
        return R.result(videoService.save(video));
    }

    @PutMapping
    @ApiOperation("修改视频")
    @PreAuthorize("@ss.hasPermi('teacher:video:update')")
    public R<Void> update(@RequestBody Video video){
        return R.result(videoService.updateById(video));
    }

    @DeleteMapping("/{videoId}")
    @ApiOperation("删除视频")
    @PreAuthorize("@ss.hasPermi('teacher:video:delete')")
    public R<Void> delete(@ApiParam("视频id")
                         @NotNull(message = "视频id为空")
                         @PathVariable Integer videoId){
        return R.result(videoService.removeById(videoId));
    }

}
