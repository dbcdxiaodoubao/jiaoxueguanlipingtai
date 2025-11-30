package com.mashang.comming;

import com.mashang.domain.entity.Video;
import com.mashang.domain.param.manage.VideoCreate;
import com.mashang.domain.param.manage.VideoUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VideoMapping {
    Video toPo(VideoCreate videoCreate);

    Video toPo(VideoUpdate videoUpdate);
}
