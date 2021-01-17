package com.itheima.health.controller;

import com.itheima.health.config.Swagger2CommonConfiguration;
import com.itheima.health.entity.Result;
import com.itheima.health.service.OrderSettingService;
import com.itheima.health.utils.poi.PoiUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author wangweili
 */
@RestController
@RequestMapping("/ordersetting")
@Api(tags = "传智健康预约模块之预约项管理")
public class OrderSettingsController {

    @Reference
    private OrderSettingService orderSettingService;

    @PostMapping("/importOrderSettings")
    @ApiOperation(value = "导入配置", notes = "预约模块导入配置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "multipartFile", value = "上传的excel对象")
    })
    @Swagger2CommonConfiguration
    @SneakyThrows
    public Result importOrderSettings(@RequestParam("excelFile") MultipartFile multipartFile) {
        List<String[]> orderSettingList = PoiUtils.readExcel(multipartFile);
        orderSettingService.importOrderSettings(orderSettingList);
        return new Result(true);
    }

    @GetMapping("/findSettingData/{year}/{month}")
    @ApiOperation(value = "查询月份", notes = "查询月份以及对应的预约设置信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "year", value = "年"),
            @ApiImplicitParam(name = "month", value = "月")
    })
    @Swagger2CommonConfiguration
    public Result findSettingData(@PathVariable("year") int year, @PathVariable("month") int month) {
        return new Result(orderSettingService.findSettingData(year, month));
    }

    @PutMapping("/updateNumberByOrderdate/{number}/{orderdate}")
    @ApiOperation(value = "预约配置", notes = "配置当日的预约人数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "number", value = "当日预约的人数"),
            @ApiImplicitParam(name = "orderdate", value = "预约日期")
    })
    @Swagger2CommonConfiguration
    public Result updateNumberByOrderdate(@PathVariable("number") int number, @PathVariable("orderdate") String orderdate) {
        return new Result(orderSettingService.updateNumberByOrderDate(number, orderdate));
    }
}
