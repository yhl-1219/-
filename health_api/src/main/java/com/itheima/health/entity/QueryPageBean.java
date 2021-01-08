package com.itheima.health.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 封装分页条件对象
 */
@Data
public class QueryPageBean implements Serializable{
    private Integer currentPage;//页码
    private Integer pageSize;//每页记录数
    private String queryString;//查询条件
}
