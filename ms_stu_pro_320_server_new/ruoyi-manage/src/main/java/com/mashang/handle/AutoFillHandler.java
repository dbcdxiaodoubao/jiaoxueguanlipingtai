package com.mashang.handle;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.mashang.constant.AutoFillConstant;
import com.ruoyi.common.utils.SecurityUtils;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

public class AutoFillHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName(AutoFillConstant.CREATE_TIME,new Date(),metaObject);
        this.setFieldValByName(AutoFillConstant.CREATE_BY, SecurityUtils.getUsername(),metaObject);
        this.setFieldValByName(AutoFillConstant.UPDATE_TIME,new Date(),metaObject);
        this.setFieldValByName(AutoFillConstant.UPDATE_BY,SecurityUtils.getUsername(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName(AutoFillConstant.UPDATE_TIME,new Date(),metaObject);
        this.setFieldValByName(AutoFillConstant.UPDATE_BY,SecurityUtils.getUsername(),metaObject);
    }
}
