package com.mashang.domain.vo.management;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.annotation.Excels;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.domain.entity.SysRole;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@ApiModel("学生信息列表")
public class StudentListVo {

    /** 用户ID */
    @ApiModelProperty(value = "用户id")
    @Excel(name = "用户序号", type = Excel.Type.EXPORT, cellType = Excel.ColumnType.NUMERIC, prompt = "用户编号")
    private Long userId;

    /** 用户账号 */
    @ApiModelProperty(value = "用户名称",required = true)
    @Excel(name = "用户名称")
    private String userName;

    /** 用户昵称 */
    @ApiModelProperty(value = "真实姓名",required = true)
    @Excel(name = "真实姓名")
    private String nickName;

    /** 手机号码 */
    @ApiModelProperty(value = "手机号码")
    @Excel(name = "手机号码", cellType = Excel.ColumnType.TEXT)
    private String phonenumber;

    /** 用户性别 */
    @ApiModelProperty(value = "用户性别（0男 1女 2未知）")
    @Excel(name = "用户性别", readConverterExp = "0=男,1=女,2=未知")
    private String sex;

    /** 账号状态（0正常 1停用） */
    @ApiModelProperty(value = "账号状态（0正常 1停用）")
    @Excel(name = "账号状态", readConverterExp = "0=正常,1=停用")
    private String status;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty(value = "年级（1-12分别是一年级到高三）")
    private Long grade;

}
