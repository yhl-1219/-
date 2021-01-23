package com.itheima.health.controller;

import com.itheima.health.config.Swagger2CommonConfiguration;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.Role;
import com.itheima.health.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author wangweili
 * @version 1.0
 * @date 2021/1/19 7:52 下午
 */
@RestController
@RequestMapping("/role")
@Api(tags = "传智健康系统模块之角色项管理")
public class RoleController {

    @Reference
    private RoleService roleService;

    @ApiOperation(value = "查询分页", notes = "分页查询所有角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "当前页"),
            @ApiImplicitParam(name = "pageSize", value = "页面大小"),
            @ApiImplicitParam(name = "queryString", value = "查询关键词")
    })
    @PostMapping("/findPage")
    @Swagger2CommonConfiguration
    @PreAuthorize("hasAuthority('ROLE_QUERY')")
    public Result findPage(@RequestBody QueryPageBean pageBean) {
        return new Result(roleService.findPage(pageBean));
    }

    @ApiOperation(value = "搜索权限", notes = "搜索所有的权限")
    @Swagger2CommonConfiguration
    @GetMapping("/findAllPermission")
    @PreAuthorize("hasAuthority('ROLE_QUERY')")
    public Result findAllPermission() {
        return new Result(roleService.findAllPermission());
    }

    @ApiOperation(value = "查找拥有权限", notes = "根据角色id查询其相应的权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色id")
    })
    @PostMapping("/findPermissionInfoById/{id}")
    @Swagger2CommonConfiguration
    @PreAuthorize("hasAuthority('ROLE_QUERY')")
    public Result findPermissionInfoById(@PathVariable("id") Integer id) {
        return new Result(roleService.findPermissionInfoById(id));
    }

    @ApiOperation(value = "查找拥有菜单", notes = "根据角色id查询其相应的菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色id")
    })
    @PostMapping("/findMenuInfoById/{id}")
    @Swagger2CommonConfiguration
    @PreAuthorize("hasAuthority('MENU_EDIT')")
    public Result findMenuInfoById(@PathVariable("id") Integer id) {
        return new Result(roleService.findMenuInfoById(id));
    }

    @ApiOperation(value = "增加角色", notes = "增加角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "role", value = "封装的角色数据")
    })
    @PostMapping("/add")
    @Swagger2CommonConfiguration
    @PreAuthorize("hasAuthority('ROLE_ADD') or hasAuthority('ROLE_EDIT')")
    public Result add(@RequestBody Role role) {
        return new Result(roleService.addOrUpdateRole(role));
    }

    @ApiOperation(value = "删除角色", notes = "根据id删除角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "要删除的角色id")
    })
    @DeleteMapping("/deleteRoleById/{id}")
    @Swagger2CommonConfiguration
    @PreAuthorize("hasAuthority('ROLE_DELETE')")
    public Result deleteRoleById(@PathVariable("id") Integer id) {
        return new Result(roleService.deleteRoleById(id));
    }

    @ApiOperation(value = "查询目录", notes = "查询所有目录")
    @GetMapping("/findAllMenu")
    @Swagger2CommonConfiguration
    @PreAuthorize("hasAuthority('MENU_QUERY')")
    public Result findAllMenu() {
        return new Result(roleService.findAllMenu());
    }
    
}
