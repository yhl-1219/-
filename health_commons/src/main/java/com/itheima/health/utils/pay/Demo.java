package com.itheima.health.utils.pay;

import com.itheima.health.utils.resources.PayConstant;

/**
 * @author luch
 * @date 2021/1/21 20:56
 */


public class Demo {
    public static void main(String[] args) throws Exception {
        String url = PayUtils.createOrder(PayConstant.ORDER_ID_PREFIX + "rt3", 1);
        System.out.println(url);
    }
}
