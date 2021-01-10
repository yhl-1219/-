package com.itheima.health.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 预约设置
 */
@Data
@TableName(value = "t_ordersetting")
@JsonInclude(JsonInclude.Include.NON_NULL)  //  序列化 字段为null 过滤
public class OrderSetting implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField(value = "ORDERDATE")
    private Date orderDate;//预约设置日期
    private int number;//可预约人数
    private int reservations;//已预约人数


}
