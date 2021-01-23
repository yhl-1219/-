package com.itheima.health.controller;

//import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.itheima.health.config.Swagger2CommonConfiguration;
import com.itheima.health.entity.Result;
import com.itheima.health.service.OrderService;
import com.itheima.health.service.PayService;
import com.itheima.health.utils.resources.PayConstant;
import io.swagger.annotations.Api;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author luch
 * @date 2021/1/21 10:07
 */

@RestController
@Api(tags = "传智健康移动模块之支付模块")
@RequestMapping("/pay")
public class PayController {

    @Reference
    private PayService payService;

    @Reference
    private OrderService orderService;

    @PostMapping("/getPayCodeInfo")
//    @ApiOperation(value = "查询所有套餐", notes = "查询所有套餐")
    @Swagger2CommonConfiguration
    public Result getPayCodeInfo(@RequestBody Map<String,String> map) throws Exception {
        Map res = payService.wxPay(map);
        return new Result(res);
    }

    @PostMapping("/PayNotify")
//    @ApiOperation(value = "查询所有套餐", notes = "查询所有套餐")
    @Swagger2CommonConfiguration
    public void payNotify(HttpServletRequest request, HttpServletResponse response) throws IOException {
//         1.接收请求参数（xml）
        ServletInputStream in = request.getInputStream();
        // 2.将xml转为java对象
        XmlMapper xmlMapper = new XmlMapper();
        Map param = xmlMapper.readValue(in, Map.class);
        System.out.println(param.get("out_trade_no"));
//        // 3.调用orderService，修改订单状态
        orderService.updateState(param);
//        // 4.返回微信平台，接收成功..
        HashMap<String, String> result = new HashMap<>();
        result.put("return_code", "SUCCESS");
        result.put("return_msg", "OK");
//        // 将map转为xml
        String xml = xmlMapper.writeValueAsString(result);
        response.setContentType("application/xml;charset=utf-8");
        response.getWriter().write(xml);
//        return new Result();
    }

    @PutMapping("/update/{id}")
    public Result update(@PathVariable("id") String id)  {
        Map param = new HashMap();
        param.put("out_trade_no", PayConstant.ORDER_ID_PREFIX + id);
        orderService.updateState(param);
        return new Result();
    }

    @PostMapping("/findState/{id}")
    public Result findState(@PathVariable("id") Integer id) {
        return new Result(orderService.findOrderStateById(id));
    }

}
