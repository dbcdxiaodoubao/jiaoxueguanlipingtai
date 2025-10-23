package com.mashang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mashang.constant.MessageConstant;
import com.mashang.constant.RoleType;
import com.mashang.domain.entity.Class;
import com.mashang.domain.entity.Task;
import com.mashang.domain.vo.student.TaskVo;
import com.mashang.mapper.ClassMapper;
import com.mashang.service.ITaskService;
import com.mashang.mapper.TaskMapper;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author 20413
 * @description 针对表【ms_task(任务表)】的数据库操作Service实现
 * @createDate 2025-10-22 18:01:40
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task>
        implements ITaskService {

    @Autowired
    private ClassMapper classMapper;

    /**
     * 查询当前学生所有学习任务列表
     *
     * @param user 学生对象
     * @return
     */
    @Override
    public List<TaskVo> listStudentTasks(SysUser user) {
        if (user == null) {
            throw new ServiceException(MessageConstant.UNKONWN_ERROR);
        }
        if (!user.getRoleId().equals(RoleType.STUDENT_TYPE)) {
            throw new ServiceException(MessageConstant.USER_NOT_STUDENT);
        }
        Long classId = user.getClassId();
        //获取班级信息
        Class aClass = classMapper.selectById(classId);
        //获取年级
        Integer grade = aClass.getGrade();
        //总业务
        List<TaskVo> taskVos = baseMapper.listStudentTasks(grade);
        if (taskVos == null) {
            return Collections.emptyList();
        }
        return taskVos;
    }
}




