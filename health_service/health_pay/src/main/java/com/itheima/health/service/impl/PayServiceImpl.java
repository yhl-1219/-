package com.itheima.health.service.impl;

import com.itheima.health.service.PayService;
import com.itheima.health.utils.pay.PayUtils;
import com.itheima.health.utils.resources.PayConstant;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * @author luch
 * @date 2021/1/21 10:14
 */

@Service
@Transactional
public class PayServiceImpl implements PayService {

    @Override
    public Map wxPay(Map<String, String> map) throws Exception {
        // 1.接收请求参数
        String oid = PayConstant.ORDER_ID_PREFIX + map.get("oid");
        String price = map.get("price");

        // 2.调用微信平台，生成预交易链接
        String pay_url = PayUtils.createOrder(oid, 1);// 写死1分钱...

        // 3.将数据设置到request域
        Map resMap = new HashMap();
        resMap.put("oid", oid);
        resMap.put("price", price);
        resMap.put("pay_url", pay_url);

        return resMap;
    }
}
