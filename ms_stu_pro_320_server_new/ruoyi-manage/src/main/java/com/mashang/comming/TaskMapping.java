package com.mashang.comming;

import com.mashang.domain.entity.Task;
import com.mashang.domain.param.manage.TaskCreate;
import com.mashang.domain.param.manage.TaskUpdate;
import com.mashang.domain.vo.management.TaskListVo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaskMapping {
    List<TaskListVo> toTaskListVoList(List<Task> records);

    Task toPo(TaskCreate taskCreate);

    Task toPo(TaskUpdate taskUpdate);
}
