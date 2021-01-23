package com.itheima.health.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.health.pojo.Member;

import java.util.Map;

public interface ReportBusinessService extends IService<Member> {

    Map findReportBusinessData();

//    void reportBusinessDataExcelDownload(Map map);
}
