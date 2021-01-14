package com.itheima.health.controller;

import com.itheima.health.config.Swagger2CommonConfiguration;
import com.itheima.health.entity.Result;
import com.itheima.health.service.OrderService;
import com.itheima.health.utils.redis.RedisUtil;
import com.itheima.health.utils.resources.RedisMessageConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Api(tags = "传智健康移动模块之预约模块")
@RequestMapping("/order")
public class OrderMobileController {

    @Reference
    private OrderService orderService;

    @PostMapping("/add")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderType", value = "预约方式"),
            @ApiImplicitParam(name = "setmealId", value = "套餐id"),
            @ApiImplicitParam(name = "sex", value = "性别"),
            @ApiImplicitParam(name = "name", value = "会员名称"),
            @ApiImplicitParam(name = "telephone", value = "电话号码"),
            @ApiImplicitParam(name = "validateCode", value = "验证码"),
            @ApiImplicitParam(name = "idCard", value = "身份证码"),
            @ApiImplicitParam(name = "orderDate", value = "预约日期"),
    })
    @ApiOperation(value = "预约方法", notes = "用于非用户/用户预约/自动注册")
    @Swagger2CommonConfiguration
    public Result add(@RequestBody Map<String, String> map) {
        String telephone = map.get("telephone");
        String validateCode = map.get("validateCode");
        String o = RedisMessageConstant.SENDTYPE_ORDER + telephone;
        if (!RedisUtil.existsKey(o)) {
            return new Result(false, "验证码已过期或不生效");
        }
        if (StringUtils.isBlank(map.get("validateCode"))) {
            return new Result(false, "验证码为空，请重填");
        }
        Integer var1 = (Integer) RedisUtil.get(o);
        String var2 = String.valueOf(var1);
        if (!var2.equals(validateCode)) {
            return new Result(false, "验证码不正确，请重试");
        }
        RedisUtil.delete(RedisMessageConstant.SENDTYPE_ORDER + telephone);
        return new Result(orderService.add(map));
    }

    @PostMapping("/search/{id}")
    @ApiOperation(value = "出票", notes = "预约成功回执")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "订单号")
    })
    @Swagger2CommonConfiguration
    public Result search(@PathVariable("id") Integer id) {
        return new Result(orderService.getById(id));
    }
}