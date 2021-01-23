package com.itheima.health.controller;

import com.itheima.health.config.Swagger2CommonConfiguration;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.User;
import com.itheima.health.service.RoleService;
import com.itheima.health.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author wangweili
 * @version 1.0
 * @date 2021/1/20 1:28 上午
 */
@Api(tags = "传智健康权限模块之用户组管理")
@RestController
@RequestMapping("/user")
public class UserController {

    @Reference
    private UserService userService;

    @Reference
    private RoleService roleService;

    @ApiOperation(value = "分页查询", notes = "根据分页参数查询结果")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "当前页"),
            @ApiImplicitParam(name = "pageSize", value = "页面大小"),
            @ApiImplicitParam(name = "queryString", value = "查询关键词")
    })
    @Swagger2CommonConfiguration
    @PostMapping("/findPage")
    @PreAuthorize("hasAuthority('USER_QUERY')")
    public Result findPage(@RequestBody QueryPageBean pageBean) {
        return new Result(userService.findPage(pageBean));
    }

    @ApiOperation(value = "查询所有角色", notes = "查询所有角色")
    @Swagger2CommonConfiguration
    @GetMapping("/findAllRole")
    @PreAuthorize("hasAuthority('USER_QUERY')")
    public Result findAllRole() {
        return new Result(roleService.list());
    }

    @ApiOperation(value = "添加用户", notes = "添加用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键"),
            @ApiImplicitParam(name = "birthday", value = "生日"),
            @ApiImplicitParam(name = "gender", value = "性别"),
            @ApiImplicitParam(name = "username", value = "用户名"),
            @ApiImplicitParam(name = "password", value = "密码"),
            @ApiImplicitParam(name = "remark", value = "标记"),
            @ApiImplicitParam(name = "station", value = "状态"),
            @ApiImplicitParam(name = "telephone", value = "电话号码"),
            @ApiImplicitParam(name = "roleIds", value = "拥有的角色")
    })
    @Swagger2CommonConfiguration
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('USER_ADD') or hasAuthority('USER_EDIT')")
    public Result add(@RequestBody User user) {
        return new Result(userService.addOrUpdateRole(user));
    }

    @ApiOperation(value = "删除用户", notes = "根据id删除用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "要删除的id")
    })
    @Swagger2CommonConfiguration
    @DeleteMapping("/deleteUserById/{id}")
    @PreAuthorize("hasAuthority('USER_DELETE')")
    public Result deleteUserById(@PathVariable("id") Integer id) {
        return new Result(userService.deleteUserById(id));
    }

    @ApiOperation(value = "查询角色组", notes = "根据用户id查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "要搜索的id")
    })
    @Swagger2CommonConfiguration
    @PostMapping("/findRoleInfoById/{id}")
    @PreAuthorize("hasAuthority('USER_QUERY')")
    public Result findRoleInfoById(@PathVariable("id") Integer id) {
        return new Result(userService.findRoleInfoById(id));
    }

    @ApiOperation(value = "修改密码", notes = "修改密码功能")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "old", value = "旧密码"),
            @ApiImplicitParam(name = "new", value = "新密码")
    })
    @Swagger2CommonConfiguration
    @PostMapping("/changePassword")
    public Result changePassword(@RequestBody Map<String, String> password) {
        return new Result(userService.changePassword(password.get("username"), password.get("old"), password.get("newPass")));
    }
}   
