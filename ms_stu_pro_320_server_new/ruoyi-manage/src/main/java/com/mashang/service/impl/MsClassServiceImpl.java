package com.mashang.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mashang.domain.entity.Class;
import com.mashang.service.IClassService;
import com.mashang.mapper.ClassMapper;
import org.springframework.stereotype.Service;

/**
* @author 20413
* @description 针对表【ms_class(班级表)】的数据库操作Service实现
* @createDate 2025-10-22 18:01:40
*/
@Service
public class MsClassServiceImpl extends ServiceImpl<ClassMapper, Class>
    implements IClassService {

}




