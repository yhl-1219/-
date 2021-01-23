package com.itheima.health.controller;


import com.itheima.health.config.Swagger2CommonConfiguration;
import com.itheima.health.entity.Result;
import com.itheima.health.service.CreateMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/createmember")
@Api(tags = "传智健康报表模块之饼状图")
public class CreateMemberController {

    @Reference
    private CreateMemberService createMemberService;

    @GetMapping("/autoGenerateMember/{id}")
    @ApiOperation(value = "一键生成虚拟用户", notes = "饼状图生成用户")
    @Swagger2CommonConfiguration
    @PreAuthorize("hasAuthority('CHECKITEM_ADD')")
    public Result autoGenerateMember(@PathVariable("id") int id){
        Result result = new Result();
        result.setFlag(createMemberService.saveMember(id));

        if (result.isFlag()){
            result.setMessage("一键生成成功");
        }else {
            throw new RuntimeException("用户生成异常");
        }
        return result;
    }

    @GetMapping("/findmemberbysex")
    @ApiOperation(value = "查询所有会员性别", notes = "饼状图查询会员性别")
    @Swagger2CommonConfiguration
    @PreAuthorize("hasAuthority('CHECKITEM_QUERY')")
    public Result findMemberBySex(){
        Result result = new Result(true,"查询会员性别成功",createMemberService.findMemberBySex());
        return result;
    }


    @GetMapping("/findmemberbyage")
    @ApiOperation(value = "查询所有会员年龄", notes = "饼状图查询会员年龄")
    @Swagger2CommonConfiguration
    @PreAuthorize("hasAuthority('CHECKITEM_QUERY')")
    public Result findMemberByAge(){
        Result result = new Result(true,"查询会员年龄成功",createMemberService.findMemberByAge());
        return result;
    }
}
