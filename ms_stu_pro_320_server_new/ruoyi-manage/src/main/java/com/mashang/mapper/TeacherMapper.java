package com.mashang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mashang.domain.vo.management.TeacherDtlVo;
import com.mashang.domain.vo.management.TeacherListVo;
import com.ruoyi.common.core.domain.entity.SysUser;

import java.util.List;

public interface TeacherMapper extends BaseMapper<SysUser> {

    /**
     * 根据老师姓名模糊查询教师信息
     * @param nickName
     * @return
     */
    List<TeacherListVo> list(String nickName);

    /**
     * 通过id查询教师详情
     * @param id
     * @return
     */
    TeacherDtlVo selectByid(Long id);
}
