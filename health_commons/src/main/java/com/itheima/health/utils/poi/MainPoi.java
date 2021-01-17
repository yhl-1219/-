package com.itheima.health.utils.poi;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

/**
 * @author wangweili 
 */
public class MainPoi {

    public static void main(String[] args) throws Exception {
            //  读取一个excel文件
            XSSFWorkbook  workbook = new XSSFWorkbook(new FileInputStream("D:\\上海-黑马程序员-课程资料\\项目-传智健康-分布式3.0\\传智健康Day05-预约管理 √\\资料\\hello.xlsx"));

            XSSFSheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {

                for (Cell cell : row) {
                    System.out.print(cell.getStringCellValue()+"\t");
                }
                System.out.println();

            }

    }
}
