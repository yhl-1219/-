package com.itheima.health.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 封装分页条件对象
 * 
 * @author wangweili
 */
@Data
public class QueryPageBean implements Serializable{

    /**
     * 页码
     */
    private Integer currentPage;

    /**
     * 每页记录数
     */
    private Integer pageSize;

    /**
     * 查询条件
     */
    private String queryString;
}
