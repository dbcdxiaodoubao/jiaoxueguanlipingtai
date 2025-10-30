package com.mashang.mapper;

import com.mashang.domain.entity.Class;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 20413
* @description 针对表【ms_class(班级表)】的数据库操作Mapper
* @createDate 2025-10-22 18:01:40
* @Entity com.mashang.domain.entity.Class
*/
public interface ClassMapper extends BaseMapper<Class> {

    List<Integer> selectClassIds(Long userId);
}




