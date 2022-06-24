package com.twt.utils;


import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

/**
 * excel 表格导出工具类
 *
 * @author shengshenglalala
 */
public class ExportUtil {
    private XSSFWorkbook wb;

    private XSSFSheet sheet;

    /**
     * @param wb
     * @param sheet
     */
    public ExportUtil(XSSFWorkbook wb, XSSFSheet sheet) {
        this.wb = wb;
        this.sheet = sheet;
    }

    /**
     * 合并单元格后给合并后的单元格加边框
     *
     * @param region
     * @param cs
     */
    public void setRegionStyle(CellRangeAddress region, XSSFCellStyle cs) {

        int toprowNum = region.getFirstRow();
        for (int i = toprowNum; i <= region.getLastRow(); i++) {
            XSSFRow row = sheet.getRow(i);
            for (int j = region.getFirstColumn(); j <= region.getLastColumn(); j++) {
                XSSFCell cell = row.getCell(j);
                cell.setCellStyle(cs);
            }
        }
    }



    /**
     * 没有行，就创建行
     *
     * @param sheet
     * @param index
     * @return
     */
    public static XSSFRow createRow(XSSFSheet sheet, Integer index) {
        XSSFRow row = sheet.getRow(index);
        if (row == null) {
            return sheet.createRow(index);
        }
        return row;
    }

    /**
     * 如果没有列，就创建列
     *
     * @param row
     * @param index
     * @return
     */
    public static XSSFCell createCell(XSSFRow row, Integer index) {
        XSSFCell cell = row.getCell(index);
        if (cell == null) {
            return row.createCell(index);
        }
        return cell;
    }
}