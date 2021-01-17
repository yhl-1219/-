package com.itheima.health.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 菜单
 * 
 * @author wangweili
 */
@Data
@TableName(value = "t_menu")
public class Menu implements Serializable{
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 访问路径
     */
    @TableField(value = "LINKURL")
    private String linkUrl;

    /**
     * 菜单项所对应的路由路径
     */
    private String path;

    /**
     * 优先级（用于排序）
     */
    private Integer priority;

    /**
     * 描述
     */
    private String description;

    /**
     * 图标
     */
    private String icon;

    /**
     * 父菜单id
     */
    @TableField(value = "PARENTMENUID")
    private Integer parentMenuId;


}
