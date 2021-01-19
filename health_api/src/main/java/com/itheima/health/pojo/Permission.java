package com.itheima.health.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 权限
 * 
 * @author wangweili
 */
@Data
@TableName(value = "t_permission")
public class Permission implements Serializable{

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 权限关键字，用于权限控制
     */
    private String keyword;

    /**
     * 描述
     */
    private String description;

}
