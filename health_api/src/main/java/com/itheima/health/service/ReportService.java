package com.itheima.health.service;

import java.util.List;
import java.util.Map;

/**
 * @author wangweili
 */
public interface ReportService{

    Map findLastyearMemberCountsByMonth();

    List<Map> getSetmealProportion();
}
