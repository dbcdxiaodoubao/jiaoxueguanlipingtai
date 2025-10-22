package com.mashang.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mashang.domain.entity.Task;
import com.mashang.service.ITaskService;
import com.mashang.mapper.TaskMapper;
import org.springframework.stereotype.Service;

/**
* @author 20413
* @description 针对表【ms_task(任务表)】的数据库操作Service实现
* @createDate 2025-10-22 18:01:40
*/
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task>
    implements ITaskService {

}




