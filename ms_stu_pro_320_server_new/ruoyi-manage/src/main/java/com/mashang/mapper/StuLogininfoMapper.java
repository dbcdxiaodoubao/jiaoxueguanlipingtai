package com.mashang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mashang.domain.vo.management.StuLoginInfoVo;
import com.mashang.domain.vo.management.UserActivity;
import com.ruoyi.system.domain.SysLogininfor;

import java.util.List;

public interface StuLogininfoMapper extends BaseMapper<SysLogininfor> {

    /**
     * 根据用户名查询学生登录日志
     * @param userName
     * @return
     */
    List<StuLoginInfoVo> list(String userName);

    /**
     * 查询用户活跃度
     * @return
     */
    List<UserActivity> getUserActivity();
}
