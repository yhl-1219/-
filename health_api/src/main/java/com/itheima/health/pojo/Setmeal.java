package com.itheima.health.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 体检套餐
 * 
 * @author wangweili
 */
@Data
@TableName(value = "t_setmeal")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Setmeal implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String name;
    private String code;
    @TableField(value = "HELPCODE")
    private String helpCode;

    /**
     * 套餐适用性别：0不限 1男 2女
     */
    private String sex;

    /**
     * 套餐适用年龄
     */
    private String age;

    /**
     * 套餐价格
     */
    private Float price;

    private String remark;

    private String attention;

    /**
     * 套餐对应图片存储路径
     */
    private String img;

    private Integer isDelete;

    /**
     * 体检套餐对应的检查组，多对多关系
     */
    @TableField(exist = false)
    private List<CheckGroup> checkGroups;

}
