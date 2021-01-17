package com.itheima.health.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 * 
 * @author wangweili
 */
@Data
@TableName(value = "t_user")
public class User implements Serializable{

    /**
     * 主键
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 性别
     */
    private String gender;

    /**
     * 用户名，唯一
     */
    @TableField(value = "USERNAME")
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态
     */
    private String station;

    /**
     * 联系电话
     */
    private String telephone;



}
