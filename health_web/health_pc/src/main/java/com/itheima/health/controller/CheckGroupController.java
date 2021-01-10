package com.itheima.health.controller;

import com.itheima.health.config.Swagger2CommonConfiguration;
import com.itheima.health.dto.CheckGroupDTO;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.entity.Result;
import com.itheima.health.service.CheckGroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

/**
 * @author wangweili
 */
@RestController
@RequestMapping("/checkgroup")
@Api(tags = "传智健康预约模块之检查组管理")
public class CheckGroupController {

    @Reference
    private CheckGroupService checkGroupService;

    @PostMapping("/findPage")
    @ApiOperation(value = "查询分页", notes = "分页查询功能")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageBean", value = "分页查询数据")
    })
    @Swagger2CommonConfiguration
    public Result findPage(@RequestBody QueryPageBean pageBean) {
        return new Result(checkGroupService.findPage(pageBean));
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加检查组", notes = "添加检查组功能")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "checkGroupDTO", value = "多表检查组")
    })
    @Swagger2CommonConfiguration
    public Result add(@RequestBody CheckGroupDTO checkGroupDTO) {
        return new Result(checkGroupService.add(checkGroupDTO));
    }

    @DeleteMapping("/deleteCheckGroupById/{id}")
    @ApiOperation(value = "删除检查组", notes = "删除检查组功能")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "要删除的id")
    })
    @Swagger2CommonConfiguration
    public Result delete(@PathVariable("id") Integer id) {
        return new Result(checkGroupService.deleteCheckGroupById(id));
    }

    @GetMapping("/findCheckItemInfoByGroupId/{id}")
    @ApiOperation(value = "搜索CheckItems", notes = "更新检查组功能的前置功能")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "要更新的检查组的id")
    })
    @Swagger2CommonConfiguration
    public Result findCheckItemInfoByGroupId(@PathVariable("id") Integer id) {
        return new Result(checkGroupService.findCheckItemInfoByGroupId(id));
    }

    @GetMapping("/findAll")
    @ApiOperation(value = "查询所有group",notes = "查询所有的检查组")
    @Swagger2CommonConfiguration
    public Result findAll() {
        return new Result(checkGroupService.list());
    }
}
