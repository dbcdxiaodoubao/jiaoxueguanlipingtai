package com.mashang.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mashang.domain.entity.Video;
import com.mashang.service.IVideoService;
import com.mashang.mapper.VideoMapper;
import org.springframework.stereotype.Service;

/**
* @author 20413
* @description 针对表【ms_video】的数据库操作Service实现
* @createDate 2025-10-24 11:05:00
*/
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video>
    implements IVideoService {

}




