package com.itheima.health.controller;

import com.itheima.health.entity.Result;
import com.itheima.health.service.MemberService;
import io.swagger.annotations.Api;
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
    public Result getMemberCount(@PathVariable("year") Integer year,@PathVariable("month") Integer month) {
        HashMap<String, Map<String, Object>> map = memberService.getMemberCount(year,month);
        return new Result(map);
    }

}
