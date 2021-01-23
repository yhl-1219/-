package com.itheima.health.service;

import java.util.Map;

/**
 * @author luch
 * @date 2021/1/21 10:14
 */


public interface PayService {

    Map wxPay(Map<String,String> map) throws Exception;
}
