import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileOutputStream;

/**
 * @author wangweili
 */
public class PoiTest {
    @Test
    @SneakyThrows
    public void poiTest() {
        XSSFWorkbook workbook = new XSSFWorkbook("/Users/wangweili/Desktop/hello.xlsx");
        XSSFSheet sheet = workbook.getSheetAt(0);
        for (Row row : sheet) {
            for (Cell cell : row) {
                System.out.println(cell.getStringCellValue());
            }
        }
    }

    @Test
    @SneakyThrows
    public void poiTest2() {
        XSSFWorkbook workbook = new XSSFWorkbook("/Users/wangweili/Desktop/hello.xlsx");
        XSSFSheet sheet = workbook.getSheetAt(0);
        int lastRowNum = sheet.getLastRowNum();
        for (int i = 0; i <= lastRowNum; i++) {
            XSSFRow row = sheet.getRow(i);
            short lastCellNum = row.getLastCellNum();
            for (short j = 0; j < lastCellNum; j++) {
                System.out.println(row.getCell(j).getStringCellValue());
            }
        }
    }

    @Test
    @SneakyThrows
    public void demo3() {
        //在内存中创建一个Excel文件
        XSSFWorkbook workbook = new XSSFWorkbook();
        //创建工作表，指定工作表名称
        XSSFSheet sheet = workbook.createSheet("传智播客");
        //创建行，0表示第一行
        XSSFRow row = sheet.createRow(0);
        //创建单元格，0表示第一个单元格
        row.createCell(0).setCellValue("编号");
        row.createCell(1).setCellValue("名称");
        row.createCell(2).setCellValue("年龄");

        XSSFRow row1 = sheet.createRow(1);
        row1.createCell(0).setCellValue("1");
        row1.createCell(1).setCellValue("小明");
        row1.createCell(2).setCellValue("10");

        XSSFRow row2 = sheet.createRow(2);
        row2.createCell(0).setCellValue("2");
        row2.createCell(1).setCellValue("小王");
        row2.createCell(2).setCellValue("20");

        //通过输出流将workbook对象输出到磁盘
        FileOutputStream out = new FileOutputStream("/Users/wangweili/Desktop/myTest.xlsx");
        workbook.write(out);
        //清空缓冲区
        out.flush();
        out.close();
        workbook.close();
    }
}
