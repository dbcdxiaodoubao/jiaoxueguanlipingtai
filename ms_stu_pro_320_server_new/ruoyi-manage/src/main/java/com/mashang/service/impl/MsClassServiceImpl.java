package com.mashang.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mashang.comming.ClassMapping;
import com.mashang.domain.entity.Class;
import com.mashang.domain.vo.teacher.TeacherClassListVo;
import com.mashang.service.IClassService;
import com.mashang.mapper.ClassMapper;
import com.ruoyi.common.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
* @author 20413
* @description 针对表【ms_class(班级表)】的数据库操作Service实现
* @createDate 2025-10-22 18:01:40
*/
@Service
@RequiredArgsConstructor
public class MsClassServiceImpl extends ServiceImpl<ClassMapper, Class>
    implements IClassService {

    private final ClassMapping classMapping;


    /**
     * 查询教师班级列表
     * @return
     */
    @Override
    public List<TeacherClassListVo> teacherClassList() {
        List<Integer> classIds = baseMapper.selectClassIds(SecurityUtils.getUserId());
        if(ObjectUtil.isEmpty(classIds))return Collections.emptyList();
        List<Class> list = lambdaQuery().in(Class::getClassId, classIds).list();
        return classMapping.toTeacherClassListVo(list);
    }
}




