package com.itheima.health.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author wangweili
 * @version 1.0
 * @date 2021/1/20 1:36 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportEntity implements Serializable {
    
    private ArrayList<Object> name;
    
    private ArrayList<Object> member;

}
