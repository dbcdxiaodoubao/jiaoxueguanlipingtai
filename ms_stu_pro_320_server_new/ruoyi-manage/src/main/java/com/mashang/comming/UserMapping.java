package com.mashang.comming;


import com.mashang.domain.query.management.UserCreatQuery;
import com.mashang.domain.vo.management.UserUpdateQuery;
import com.ruoyi.common.core.domain.entity.SysUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapping {

    UserMapping INSTANCE = Mappers.getMapper(UserMapping.class);

    SysUser toSysUser(UserCreatQuery userCreatQuery);

    SysUser toUpdate(UserUpdateQuery userUpdateQuery);
}
