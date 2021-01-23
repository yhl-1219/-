package com.itheima.health.controller;

import com.itheima.health.config.Swagger2CommonConfiguration;
import com.itheima.health.entity.ReportMemberCondition;
import com.itheima.health.entity.Result;
import com.itheima.health.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author wangweili
 */
@RestController
@RequestMapping("/report")
@Api(tags = "传智健康报表模块之报表项管理")
public class ReportController {

    @Reference
    private MemberService memberService;

    @PostMapping("/getMemberCount")
    @ApiOperation(value = "计算月份", notes = "通过year和month计算会员注册数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "beginTime", value = "起始时间"),
            @ApiImplicitParam(name = "endTime", value = "结束时间"),
            @ApiImplicitParam(name = "type", value = "类型 年/月/日")
    })
    @Swagger2CommonConfiguration
    @PreAuthorize("hasAuthority('REPORT_VIEW')")
    public Result getMemberCount(@RequestBody ReportMemberCondition condition) {
        return new Result(memberService.getMemberCount(condition));
    }

    @PostMapping("/getSetmealCount")
    @ApiOperation(value = "饼图生成", notes = "通过计算套餐预定总数来生成饼图")
    @Swagger2CommonConfiguration
    @PreAuthorize("hasAuthority('REPORT_VIEW')")
    public Result getSetmealCount() {
        return new Result(memberService.getSetmealCount());
    }

}