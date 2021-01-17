package com.itheima.health.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * 检查项
 * 
 * @author wangweili
 */
@TableName(value = "t_checkitem")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CheckItem implements Serializable {

    /**
     * 主键
     */
    @TableId(value = "id",type = IdType.AUTO)

    private Integer id;

    /**
     * 项目编码
     */
    private String code;

    /**
     * 项目名称
     */
    private String name;

    /**
     * 适用性别
     */
    private String sex;

    /**
     * 适用年龄（范围），例如：20-50
     */
    private String age;

    /**
     * 价格
     */
    private Float price;

    /**
     * 检查项类型，分为检查和检验两种类型
     */
    private String type;

    /**
     * 项目说明
     */
    private String remark;

    /**
     * 注意事项
     */
    private String attention;

    /**
     * 是否删除  1 表示删除  0 表示未删除  添加默认未删除
     */
    private Integer isDelete;

}
