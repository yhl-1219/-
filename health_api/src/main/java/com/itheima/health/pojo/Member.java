package com.itheima.health.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 会员
 * 
 * @author wangweili
 */
@Data
@Builder
@TableName(value = "t_member")
public class Member implements Serializable{

    /**
     * 主键
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 档案号
     */
    @TableField(value = "FILENUMBER")
    private String fileNumber;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String sex;

    /**
     * 身份证号
     */
    @TableField(value = "IDCARD")
    private String idCard;

    /**
     * 手机号
     */
    @TableField(value = "PHONENUMBER")
    private String phoneNumber;

    /**
     * 注册时间
     */
    @TableField(value = "REGTIME")
    private Date regTime;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 出生日期
     */
    private Date birthday;

    /**
     * 备注
     */
    private String remark;

}
