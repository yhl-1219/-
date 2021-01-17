package com.itheima.health.utils.poi;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author wangweili 
 */
public class MainPoi1 {

    public static void main(String[] args) throws IOException {
           //  生成一个excel 文件
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        XSSFSheet  sheet = xssfWorkbook.createSheet("黑马");
        XSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("姓名");
        row.createCell(1).setCellValue("年纪");
        row.createCell(2).setCellValue("学科");

        XSSFRow row1 = sheet.createRow(1);

        row1.createCell(0).setCellValue("小明");
        row1.createCell(1).setCellValue("23");
        row1.createCell(2).setCellValue("java");

        XSSFRow row2 = sheet.createRow(2);

        row2.createCell(0).setCellValue("小菜");
        row2.createCell(1).setCellValue("22");
        row2.createCell(2).setCellValue("前端");


        //  写出去
         xssfWorkbook.write(new FileOutputStream(new File("d:\\heima.xlsx")));

         xssfWorkbook.close();

    }
}
