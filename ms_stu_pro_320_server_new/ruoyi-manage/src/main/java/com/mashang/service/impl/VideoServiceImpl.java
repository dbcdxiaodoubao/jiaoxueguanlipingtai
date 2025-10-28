package com.mashang.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mashang.domain.entity.Video;
import com.mashang.domain.query.manage.VideoPageQuery;
import com.mashang.domain.vo.management.VideoVo;
import com.mashang.service.IVideoService;
import com.mashang.mapper.VideoMapper;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.stereotype.Service;

/**
* @author 20413
* @description 针对表【ms_video】的数据库操作Service实现
* @createDate 2025-10-24 11:05:00
*/
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video>
    implements IVideoService {

    /**
     * 视频列表
     * @param query
     * @return
     */
    @Override
    public TableDataInfo pageQuery(VideoPageQuery query) {
        Page<Video> page=new Page<>(query.getPageNum(),query.getPageSize());

        return new TableDataInfo(baseMapper.pageQuery(page,query.getGrade()),page.getTotal());
    }

    /**
     * 查询视频详情
     * @param videoId
     * @return
     */
    @Override
    public VideoVo queryById(Integer videoId) {
        return baseMapper.query(videoId);
    }
}




