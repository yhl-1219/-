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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @ApiOperation(value = "查询分页",notes = "分页查询功能")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageBean",value = "分页查询数据")
    })
    @Swagger2CommonConfiguration
    public Result findPage(@RequestBody QueryPageBean pageBean) {
        return new Result(checkGroupService.findPage(pageBean));
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加检查组",notes = "添加检查组功能")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "",value = "")
    })
    @Swagger2CommonConfiguration
    public Result add(CheckGroupDTO checkGroupDTO) {
        return new Result(checkGroupService.add(checkGroupDTO));
    }

}
