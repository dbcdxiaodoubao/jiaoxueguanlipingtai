package com.mashang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mashang.domain.vo.management.AdminDtlVo;
import com.mashang.domain.vo.management.AdminListVo;
import com.ruoyi.common.core.domain.entity.SysUser;

import java.util.List;

public interface AdminMapper extends BaseMapper<SysUser> {

    /**
     * 查询管理员信息列表
     * @param nickName
     * @return
     */
    List<AdminListVo> list(String nickName);

    /**
     * 通过id查询管理员详情
     * @param userId
     * @return
     */
    AdminDtlVo selectById(Long userId);

    /**
     * 通过用户id封禁用户
     * @param userId
     * @return
     */
    Integer banById(Long userId);

    /**
     * 通过用户id解禁用户
     * @param userId
     * @return
     */
    Integer noBanById(Long userId);
}
