package com.mashang.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mashang.domain.vo.management.AdminDtlVo;
import com.mashang.domain.vo.management.AdminListVo;
import com.mashang.mapper.AdminMapper;
import com.mashang.service.IAdminService;
import com.ruoyi.common.core.domain.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, SysUser>
        implements IAdminService {

    @Autowired
    AdminMapper adminMapper;

    @Override
    public List<AdminListVo> list(String nickName) {
        return adminMapper.list(nickName);
    }

    @Override
    public AdminDtlVo selectById(Long userId) {
        return adminMapper.selectById(userId);
    }

    @Override
    public Integer banById(Long userId) {
        return adminMapper.banById(userId);
    }

    @Override
    public Integer noBanById(Long userId) {
        return adminMapper.noBanById(userId);
    }
}
