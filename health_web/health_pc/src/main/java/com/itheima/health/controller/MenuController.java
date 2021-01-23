package com.itheima.health.controller;

import com.itheima.health.config.Swagger2CommonConfiguration;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.Menu;
import com.itheima.health.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/menu")
@Api(tags = "传智健康系统模块之菜单项管理")
public class MenuController {

    @Reference
    private MenuService menuService;

    @ApiOperation(value = "查询所有菜单", notes = "查询所有的菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名")
    })
    @Swagger2CommonConfiguration
    @PostMapping("/findAllMenuByUsername/{username}")
    public Result findAllMenu(@PathVariable("username") String username) {
        Map map = menuService.findAllMenuByUsername(username);
        return new Result(map);
    }

    @ApiOperation(value = "查询分页", notes = "分页查询所有菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "当前页"),
            @ApiImplicitParam(name = "pageSize", value = "页面大小"),
            @ApiImplicitParam(name = "queryString", value = "查询关键词")
    })
    @PostMapping("/findPage")
    @Swagger2CommonConfiguration
    public Result findPage(@RequestBody QueryPageBean pageBean) {
        return new Result(menuService.findPage(pageBean));
    }

    @ApiOperation(value = "增加菜单", notes = "增加菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "menu", value = "封装的菜单数据")
    })
    @PostMapping("/add")
    @Swagger2CommonConfiguration
    @PreAuthorize("hasAuthority('MENU_ADD') or hasAuthority('MENU_EDIT')")
    public Result add(@RequestBody Menu menu) {
        return new Result(menuService.addOrUpdateMenu(menu));
    }

    @ApiOperation(value = "删除菜单", notes = "根据id删除菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "要删除的菜单id")
    })
    @DeleteMapping("/deleteMenuById/{id}")
    @Swagger2CommonConfiguration
    @PreAuthorize("hasAuthority('MENU_DELETE')")
    public Result deleteMenuById(@PathVariable("id") Integer id) {
        return new Result(menuService.deleteMenuById(id));
    }
}
