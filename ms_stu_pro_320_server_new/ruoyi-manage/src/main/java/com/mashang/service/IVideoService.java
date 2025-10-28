package com.mashang.service;

import com.mashang.domain.entity.Video;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mashang.domain.query.manage.VideoPageQuery;
import com.mashang.domain.vo.management.VideoVo;
import com.ruoyi.common.core.page.TableDataInfo;

/**
* @author 20413
* @description 针对表【ms_video】的数据库操作Service
* @createDate 2025-10-24 11:05:00
*/
public interface IVideoService extends IService<Video> {

    /**
     * 视频列表
     * @param query
     * @return
     */
    TableDataInfo pageQuery(VideoPageQuery query);

    /**
     * 查询视频详情
     * @param videoId
     * @return
     */
    VideoVo queryById(Integer videoId);
}
