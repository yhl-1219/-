package com.itheima.health.controller;

import com.itheima.health.entity.Result;
import com.itheima.health.service.CheckItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

/**
 * @author wangweili
 */
@RestController
@RequestMapping("/checkitem")
@Api(tags = "传智健康预约模块之检查项管理")
public class CheckItemController {

    @Reference
    private CheckItemService checkItemService;

    @GetMapping("/findAll")
    @ApiOperation(value = "查询功能", notes = "查询全体检查项")
    public Result findPage() {
        return new Result(checkItemService.list());
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除功能", notes = "根据id删除指定的检查项")
    public Result delete(@PathVariable("id") Integer id) {
        return new Result(checkItemService.removeById(id));
    }
}
