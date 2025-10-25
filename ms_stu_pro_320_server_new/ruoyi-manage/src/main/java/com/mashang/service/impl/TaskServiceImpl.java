package com.mashang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mashang.constant.MessageConstant;
import com.mashang.constant.RoleType;
import com.mashang.domain.entity.Class;
import com.mashang.domain.entity.Task;
import com.mashang.domain.entity.TaskTest;
import com.mashang.domain.vo.management.TaskDtlVo;
import com.mashang.domain.vo.student.TaskListVo;
import com.mashang.mapper.ClassMapper;
import com.mashang.mapper.TaskTestMapper;
import com.mashang.service.ITaskService;
import com.mashang.mapper.TaskMapper;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.system.mapper.SysRoleMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private TaskTestMapper taskTestMapper;

    /**
     * 查询当前学生所有学习任务列表
     *
     * @param userId 学生id
     * @return
     */
    @Override
    public List<TaskListVo> listStudentTasks(Long userId) {
        SysUser user = sysUserMapper.selectUserById(userId);
        if (user == null) {
            throw new ServiceException(MessageConstant.UNKONWN_ERROR);
        }
        Long roleId = sysRoleMapper.selectByUserId(userId);
        if (!roleId.equals(RoleType.STUDENT_TYPE)) {
            throw new ServiceException(MessageConstant.USER_NOT_STUDENT);
        }
        Long classId = user.getClassId();
        //获取班级信息
        Class aClass = classMapper.selectById(classId);
        //获取年级
        Integer grade = aClass.getGrade();
        //总业务
        List<TaskListVo> taskVos = baseMapper.listStudentTasks(grade,userId);
        if (taskVos == null) {
            return Collections.emptyList();
        }
        return taskVos;
    }

    /**
     * 新增任务
     * @param task
     * @param testIds
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean add(Task task, List<Integer> testIds) {
        //新增任务记录
        save(task);
        //绑定试卷数据
        for (Integer testId : testIds) {
            taskTestMapper.insert(new TaskTest().setTaskId(task.getTaskId()).setTestId(testId));
        }
        return true;
    }

    /**
     * 修改任务
     * @param task
     * @param testIds
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(Task task, ArrayList<Integer> testIds) {
        //修改任务记录
        updateById(task);
        //删除原来的试卷绑定
        taskTestMapper.delete(new LambdaQueryWrapper<TaskTest>().eq(TaskTest::getTaskId,task.getTaskId()));
        //更新试卷绑定
        for (Integer testId : testIds) {
            taskTestMapper.insert(new TaskTest().setTaskId(task.getTaskId()).setTestId(testId));
        }
        return true;
    }

    /**
     * 任务详情
     * @param taskId
     * @return
     */
    @Override
    public TaskDtlVo detail(Integer taskId) {
        return baseMapper.detail(taskId);
    }
}




