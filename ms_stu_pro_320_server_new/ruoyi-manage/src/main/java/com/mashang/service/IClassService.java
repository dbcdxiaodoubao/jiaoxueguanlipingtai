package com.mashang.service;

import com.mashang.domain.entity.Class;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mashang.domain.vo.teacher.TeacherClassListVo;

import java.util.List;

/**
* @author 20413
* @description 针对表【ms_class(班级表)】的数据库操作Service
* @createDate 2025-10-22 18:01:40
*/
public interface IClassService extends IService<Class> {

    /**
     * 获取教师班级列表
     * @return
     */
    List<TeacherClassListVo> teacherClassList();

}
