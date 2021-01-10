package com.itheima.health.controller;

import com.itheima.health.config.Swagger2CommonConfiguration;
import com.itheima.health.entity.Result;
import com.itheima.health.service.OrderSettingService;
import com.itheima.health.utils.poi.POIUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
        List<String[]> orderSettingList = POIUtils.readExcel(multipartFile);
        orderSettingService.importOrderSettings(orderSettingList);
        return new Result(true);
    }
}
