package com.itheima.health.controller;

import com.itheima.health.config.Swagger2CommonConfiguration;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.CheckItem;
import com.itheima.health.service.CheckItemService;
import io.swagger.annotations.*;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author wangweili
 */
@RestController
@RequestMapping("/checkitem")
@Api(tags = "传智健康预约模块之检查项管理")
public class CheckItemController {

    /**
     * dubbo远程调用{@link CheckItemService}
     */
    @Reference
    private CheckItemService checkItemService;

    @GetMapping("/findAll")
    @ApiOperation(value = "查询功能", notes = "查询全体检查项")
    @Swagger2CommonConfiguration
    @PreAuthorize("hasAuthority('CHECKITEM_QUERY')")
    public Result findPage() {
        return new Result(checkItemService.list());
    }

    @DeleteMapping("/deleteCheckItemById/{id}")
    @ApiOperation(value = "删除功能", notes = "根据id删除指定的检查项")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "要删除的id")
    })
    @Swagger2CommonConfiguration
    @PreAuthorize("hasAuthority('CHECKITEM_DELETE')")
    public Result delete(@PathVariable("id") Integer id) {
        CheckItem checkItem = new CheckItem();
        checkItem.setId(id);
        checkItem.setIsDelete(1);
        return new Result(checkItemService.updateById(checkItem));
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增功能", notes = "添加或更新新的checkItem")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "checkItem", value = "包装的CheckItem对象")
    })
    @Swagger2CommonConfiguration
    @PreAuthorize("hasAuthority('CHECKITEM_ADD') or hasAuthority('CHECKITEM_EDIT')")
    public Result add(@RequestBody CheckItem checkItem) {
        return new Result(checkItemService.saveOrUpdate(checkItem));
    }

    @PostMapping("/findPage")
    @ApiOperation(value = "分页查询", notes = "根据分页条件查询分页数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageBean", value = "分页查询数据")
    })
    @Swagger2CommonConfiguration
    @PreAuthorize("hasAuthority('CHECKITEM_QUERY')")
    public Result findPage(@RequestBody QueryPageBean pageBean) {
        return new Result(checkItemService.findPage(pageBean));
    }

}