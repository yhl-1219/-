package com.itheima.health.controller;

import com.itheima.health.config.Swagger2CommonConfiguration;
import com.itheima.health.entity.Result;
import com.itheima.health.service.ReportBusinessService;
import com.itheima.health.utils.resources.MessageConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

@RequestMapping("/reportBusiness")
@RestController
@Api(tags = "传智健康报表模块之运营数据统计")
public class ReportBusinessController {

    @Reference
    private ReportBusinessService reportBusinessService;


    @PostMapping("/findReportBusinessData")
    @ApiOperation(value = "运营数据统计", notes = "会员和预约数据")
    @Swagger2CommonConfiguration
    @PreAuthorize("hasAuthority('REPORT_VIEW')")
    public Result findReportBusinessData() {
        Map map = reportBusinessService.findReportBusinessData();
        if (map != null) {
            return new Result(true, MessageConstant.GET_BUSINESS_REPORT_SUCCESS, map);
        } else {
            return new Result(false, MessageConstant.GET_BUSINESS_REPORT_FAIL, map);
        }
    }

    @GetMapping("/exportBusinessReport")
    @ApiOperation(value = "运营数据统计生成Excel", notes = "Excel生成并导出")
    @Swagger2CommonConfiguration
    public Result exportBusinessReport(HttpServletRequest request, HttpServletResponse response) {
        return xlsHandler(request, response);
    }

    private Result xlsHandler(HttpServletRequest request, HttpServletResponse response) {
        try {
            Map<String, Object> result = reportBusinessService.findReportBusinessData();
            //取出返回结果数据，准备将报表数据写入到Excel文件中
            String reportDate = (String) result.get("reportDate");
            Integer todayNewMember = (Integer) result.get("todayNewMember");
            Integer totalMember = (Integer) result.get("totalMember");
            Integer thisWeekNewMember = (Integer) result.get("thisWeekNewMember");
            Integer thisMonthNewMember = (Integer) result.get("thisMonthNewMember");
            Integer todayOrderNumber = (Integer) result.get("todayOrderNumber");
            Integer thisWeekOrderNumber = (Integer) result.get("thisWeekOrderNumber");
            Integer thisMonthOrderNumber = (Integer) result.get("thisMonthOrderNumber");
            Integer todayVisitsNumber = (Integer) result.get("todayVisitsNumber");
            Integer thisWeekVisitsNumber = (Integer) result.get("thisWeekVisitsNumber");
            Integer thisMonthVisitsNumber = (Integer) result.get("thisMonthVisitsNumber");
            List<Map> hotSetmeal = (List<Map>) result.get("hotSetmeal");
            String filePath = request.getSession().getServletContext().getRealPath("template") + File.separator + "report_business.xlsx";
            String var1 = System.getProperty("user.dir");
            XSSFWorkbook excel = new XSSFWorkbook(var1 + "/health_web/health_pc/src/main/resources/static/template/report_business.xlsx");
            XSSFSheet sheet = excel.getSheetAt(0);

            XSSFRow row = sheet.getRow(1);
            //日期
            row.getCell(13).setCellValue(reportDate);

            row = sheet.getRow(3);
            //新增会员数（本日）
            row.getCell(6).setCellValue(todayNewMember);
            row.getCell(12).setCellValue(totalMember);//总会员数

            row = sheet.getRow(4);
            row.getCell(6).setCellValue(thisWeekNewMember);//本周新增会员数
            row.getCell(12).setCellValue(thisMonthNewMember);//本月新增会员数

            row = sheet.getRow(6);
            row.getCell(6).setCellValue(todayOrderNumber);//今日预约数
            row.getCell(12).setCellValue(todayVisitsNumber);//今日到诊数

            row = sheet.getRow(7);
            row.getCell(6).setCellValue(thisWeekOrderNumber);//本周预约数
            row.getCell(12).setCellValue(thisWeekVisitsNumber);//本周到诊数

            row = sheet.getRow(8);
            row.getCell(6).setCellValue(thisMonthOrderNumber);//本月预约数
            row.getCell(12).setCellValue(thisMonthVisitsNumber);//本月到诊数

            int rowNum = 11;
            for (int i = 11; i < 40; i++) {
                row = sheet.getRow(i);
                row.getCell(3).setCellValue("");//套餐名称
                row.getCell(6).setCellValue("");//预约数量
                row.getCell(9).setCellValue("");//占比
                row.getCell(12).setCellValue("");
            }
            for (Map map : hotSetmeal) {//热门套餐
                String name = (String) map.get("name");
                Long setmeal_count = (Long) map.get("setmeal_count");
                String proportion = (String) map.get("proportion");
                String remark = (String) map.get("remark");
                row = sheet.getRow(rowNum++);
                row.getCell(3).setCellValue(name);//套餐名称
                row.getCell(6).setCellValue(setmeal_count);//预约数量
                row.getCell(9).setCellValue(proportion);//占比
                row.getCell(12).setCellValue(remark);
            }
            //使用输出流进行表格下载,基于浏览器作为客户端下载
            OutputStream out = response.getOutputStream();
            response.setContentType("application/vnd.ms-excel");//代表的是Excel文件类型
            response.setHeader("content-Disposition", "attachment;filename=report_business.xlsx");//指定以附件形式进行下载
            excel.write(out);

            out.flush();
            out.close();
            excel.close();
            return null;
        } catch (Exception e) {
            return new Result(false, MessageConstant.GET_BUSINESS_REPORT_FAIL);
        }
    }
}


