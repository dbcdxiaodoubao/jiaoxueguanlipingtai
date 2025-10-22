package com.mashang.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mashang.domain.entity.TaskTest;
import com.mashang.service.ITaskTestService;
import com.mashang.mapper.TaskTestMapper;
import org.springframework.stereotype.Service;

/**
* @author 20413
* @description 针对表【ms_task_test(任务-试卷表)】的数据库操作Service实现
* @createDate 2025-10-22 18:01:40
*/
@Service
public class TaskTestServiceImpl extends ServiceImpl<TaskTestMapper, TaskTest>
    implements ITaskTestService {

}




