package com.itheima.health.controller;

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
import org.springframework.web.bind.annotation.*;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
@Api(tags = "传智健康移动模块之套餐模块")
@RequestMapping("/setmeal")
public class SetmealMobileController {

    @Reference
    private SetmealService setmealService;

    @GetMapping("/findAll")
    @ApiOperation(value = "查询所有套餐", notes = "查询所有套餐")
    @Swagger2CommonConfiguration
    public Result findAll() {
        return new Result(setmealService.list());
    }

    @GetMapping("/findSetMealDetail")
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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "套餐id")})
    @Swagger2CommonConfiguration
    public Result findById(@RequestParam("id") Integer id) {
        return new Result(setmealService.getById(id));
    }

    @PostMapping("/generateCode/{telephone}")
    @Swagger2CommonConfiguration
    public Result generateCode(@PathVariable("telephone") String telephone) {
        int o = new Random().nextInt(900000) + 100000;
        RedisUtil.set(RedisMessageConstant.SENDTYPE_ORDER + telephone, o, 5, TimeUnit.MINUTES);
        return new Result(o);
    }

}
