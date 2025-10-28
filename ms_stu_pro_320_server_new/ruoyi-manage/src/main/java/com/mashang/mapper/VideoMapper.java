package com.mashang.mapper;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mashang.domain.entity.Video;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mashang.domain.vo.management.VideoListVo;
import com.mashang.domain.vo.management.VideoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 20413
* @description 针对表【ms_video】的数据库操作Mapper
* @createDate 2025-10-24 11:05:00
* @Entity com.mashang.domain.entity.Video
*/
public interface VideoMapper extends BaseMapper<Video> {

    List<VideoListVo> pageQuery(@Param("page") Page<Video> page, @Param("grade") Integer grade);

    VideoVo query(Integer videoId);
}




