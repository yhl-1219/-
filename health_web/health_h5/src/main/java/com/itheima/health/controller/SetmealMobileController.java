package com.itheima.health.controller;

import com.itheima.health.config.RabbitMqConfig;
import com.itheima.health.config.Swagger2CommonConfiguration;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.Setmeal;
import com.itheima.health.service.SetmealService;
import com.itheima.health.utils.redis.RedisUtil;
import com.itheima.health.utils.resources.MessageConstant;
import com.itheima.health.utils.resources.RedisMessageConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * @author wangweili 
 */
@RestController
@Api(tags = "传智健康移动模块之套餐模块")
@RequestMapping("/setmeal")
public class SetmealMobileController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Reference
    private SetmealService setmealService;

    @GetMapping("/findAll")
    @ApiOperation(value = "查询所有套餐", notes = "查询所有套餐")
    @Swagger2CommonConfiguration
    public Result findAll() {
        return new Result(setmealService.list());
    }

    @GetMapping("/findSetMealDetail")
    @ApiOperation(value = "查询套餐细节", notes = "使用id查询套餐细节")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "套餐id")})
    @Swagger2CommonConfiguration
    public Result findSetMealDetail(@RequestParam("id") Integer id) {
        try {
            Setmeal setmeal = setmealService.findSetMealDetailById(id);
            return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS, setmeal);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_SETMEAL_FAIL);
        }
    }

    @GetMapping("/findById")
    @ApiOperation(value = "查找套餐", notes = "查找套餐")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "套餐id")})
    @Swagger2CommonConfiguration
    public Result findById(@RequestParam("id") Integer id) {
        return new Result(setmealService.getById(id));
    }

    @PostMapping("/generateCode/{telephone}")
    @ApiOperation(value = "发送验证码", notes = "发送验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "telephone", value = "电话号码")})
    @Swagger2CommonConfiguration
    public Result generateCode(@PathVariable("telephone") String telephone) {
        rabbitTemplate.convertAndSend(RabbitMqConfig.TEST_EXCHANGE, "email-key", telephone);
        return new Result(true);
    }

}
