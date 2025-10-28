package com.mashang.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mashang.domain.vo.management.StuLoginInfoVo;
import com.mashang.domain.vo.management.UserActivity;
import com.mashang.mapper.StuLogininfoMapper;
import com.mashang.service.IStuLogininfoService;
import com.ruoyi.system.domain.SysLogininfor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class StuLogininfoServiceImpl extends ServiceImpl<StuLogininfoMapper, SysLogininfor>
        implements IStuLogininfoService {

    @Autowired
    StuLogininfoMapper stuLogininfoMapper;

    @Override
    public List<StuLoginInfoVo> list(String userName) {
        return stuLogininfoMapper.list(userName);
    }

    @Override
    public List<UserActivity> getUserActivity() {
        return stuLogininfoMapper.getUserActivity();
    }
}
