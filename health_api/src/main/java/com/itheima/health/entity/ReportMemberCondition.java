package com.itheima.health.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wangweili
 * @version 1.0
 * @date 2021/1/20 10:50 上午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportMemberCondition implements Serializable {

    private Date beginTime;

    private Date endTime;

    /**
     * 0 - year
     * 1 - month
     * 2 - week
     * 3 - day
     */
    private Integer type;

}
