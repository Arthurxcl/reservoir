package com.henu.reservoir.util;

import com.henu.reservoir.domain.MeasuredResultDao;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * 从excel中提取实测水位数据存入数据库
 */

public class ExtractMeasuredLevel {
    private XSSFWorkbook xssfWorkbook;
    private Integer reservoirId;

    /**
     * 构造函数处理文件流
     * @param fileInputStream
     * @param reservoirID
     * @throws IOException
     */
    public ExtractMeasuredLevel(FileInputStream fileInputStream, Integer reservoirID) throws IOException {
        //excel文件
        xssfWorkbook = new XSSFWorkbook(fileInputStream);
        reservoirId = reservoirID;
    }

    public ArrayList<MeasuredResultDao> ReadDataFromExcel() throws ParseException {
        ArrayList<MeasuredResultDao> allResult = new ArrayList<MeasuredResultDao>();
        //获取工作表
        XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
        //提取每一行数据存入数组中
        for(int i=1; i<sheet.getLastRowNum()+1; i++) {
            XSSFRow row = sheet.getRow(i);
            String name = row.getCell(0).getStringCellValue();
            double waterLevel = row.getCell(1).getNumericCellValue();
            double measuredStorage = row.getCell(2).getNumericCellValue();
            Date dateStr = row.getCell(3).getDateCellValue();
            /*DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateFormat.parse(dateStr);*/

            allResult.add(new MeasuredResultDao(0, Double.toString(waterLevel), Double.toString(measuredStorage), name, dateStr, reservoirId));
        }
        return allResult;
    }
}
