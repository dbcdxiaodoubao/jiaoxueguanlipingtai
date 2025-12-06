package com.mashang.domain.vo.management;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("用户修改")
public class UserUpdateQuery {

    /** 用户ID */
    @ApiModelProperty(value = "用户id")
    @Excel(name = "用户序号", type = Excel.Type.EXPORT, cellType = Excel.ColumnType.NUMERIC, prompt = "用户编号")
    private Long userId;

    /** 用户账号 */
    @ApiModelProperty(value = "用户名称",required = true)
    @Excel(name = "用户名称")
    private String userName;

    /** 密码 */
    @ApiModelProperty(value = "密码" ,required = true)
    private String password;

    /** 用户昵称 */
    @ApiModelProperty(value = "真实姓名",required = true)
    @Excel(name = "真实姓名")
    private String nickName;

    /** 年龄 */
    @ApiModelProperty(value = "年龄")
    private Long age;

    @ApiModelProperty(value = "角色ID(3为学生、4为教师、5为管理员)",required = true)
    private Long roleId;

    /** 用户性别 */
    @ApiModelProperty(value = "用户性别（0男 1女 2未知）")
    @Excel(name = "用户性别", readConverterExp = "0=男,1=女,2=未知")
    private String sex;

    /** 出生日期 */
    @ApiModelProperty(value = "出生日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;

    /** 手机号码 */
    @ApiModelProperty(value = "手机号码")
    @Excel(name = "手机号码", cellType = Excel.ColumnType.TEXT)
    private String phonenumber;

    /** 账号状态（0正常 1停用） */
    @ApiModelProperty(value = "账号状态（0正常 1停用）")
    @Excel(name = "账号状态", readConverterExp = "0=正常,1=停用")
    private String status;
}
