package com.mashang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mashang.domain.vo.management.StuLoginInfoVo;
import com.mashang.domain.vo.management.UserActivity;
import com.ruoyi.system.domain.SysLogininfor;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IStuLogininfoService extends IService<SysLogininfor> {
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
