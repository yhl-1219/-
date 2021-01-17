package com.itheima.health.controller;

import com.itheima.health.config.Swagger2CommonConfiguration;
import com.itheima.health.entity.Result;
import com.itheima.health.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/report")
@Api(tags = "传智健康报表模块之报表项管理")
public class ReportController {

    @Reference
    private MemberService memberService;

    @PostMapping("/getMemberCount/{year}/{month}")
    @ApiOperation(value = "计算月份", notes = "通过year和month计算会员注册数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "year", value = "年"),
            @ApiImplicitParam(name = "month", value = "月份")
    })
    @Swagger2CommonConfiguration
    public Result getMemberCount(@PathVariable("year") Integer year, @PathVariable("month") Integer month) {
        HashMap<String, Map<String, Object>> map = memberService.getMemberCount(year, month);
        return new Result(map);
    }

    @PostMapping("/getSetmealCount")
    @ApiOperation(value = "饼图生成", notes = "通过计算套餐预定总数来生成饼图")
    @Swagger2CommonConfiguration
    public Result getSetmealCount() {
        return new Result(memberService.getSetmealCount());
    }

}
