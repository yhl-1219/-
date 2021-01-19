package com.itheima.health.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 角色
 * 
 * @author wangweili 
 */
@Data
@TableName(value = "t_role")
public class Role implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色关键字，用于权限控制
     */
    private String keyword;

    /**
     * 描述
     */
    private String description;

    @TableField(exist = false)
    private List<Integer> permissionIds;

}
