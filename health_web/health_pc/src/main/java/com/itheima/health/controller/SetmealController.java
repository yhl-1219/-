package com.itheima.health.controller;

import com.itheima.health.config.Swagger2CommonConfiguration;
import com.itheima.health.dto.SetmealDTO;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.entity.Result;
import com.itheima.health.service.SetmealService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/setmeal")
@Api(tags = "传智健康预约模块之套餐管理")
public class SetmealController {

    @Reference
    private SetmealService setmealService;

    @PostMapping("/findPage")
    @ApiOperation(value = "套餐分页", notes = "套餐列表分页查询功能")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "queryPageBean", value = "分页数据")
    })
    @Swagger2CommonConfiguration
    public Result findPage(@RequestBody QueryPageBean queryPageBean) {
        return new Result(setmealService.findPage(queryPageBean));
    }

    @PostMapping("/add")
    @ApiOperation(value = "增加套餐", notes = "套餐列表增添套餐功能")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "setmealDTO", value = "要新增的数据")
    })
    @Swagger2CommonConfiguration
    public Result add(@RequestBody SetmealDTO setmealDTO) {
        return new Result(setmealService.saveUpdate(setmealDTO));
    }

    @DeleteMapping("/deleteSetmealById/{id}")
    @ApiOperation(value = "删除功能",notes = "根据id删除套餐功能")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "要删除的数据的id")
    })
    @Swagger2CommonConfiguration
    public Result deleteSetmealById(@PathVariable("id") Integer id) {
        return new Result(setmealService.delete(id));
    }

    @PostMapping("/findGroupIdsBySetmealId/{id}")
    @ApiOperation(value = "查询功能",notes = "更新功能的前置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "要查询的数据的id")
    })
    @Swagger2CommonConfiguration
    public Result findGroupIdsBySetmealId(@PathVariable("id") Integer id) {
        return new Result(setmealService.findGroupIdsBySetmealId(id));
    }
    
}
