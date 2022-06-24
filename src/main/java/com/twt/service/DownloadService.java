package com.twt.service;


import com.twt.entity.Apply;
import com.twt.entity.Confirm;
import com.twt.mapper.ApplyMapper;
import com.twt.utils.ExportUtil;
import com.twt.utils.ZipUtil;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;


@Service
public class DownloadService {

    @Autowired
    ApplyService applyService;

    @Autowired
    ConfirmService confirmService;

    public void exportApplyInfo(HttpServletResponse response) throws IOException {
        //文件名
        String fileName = "applyInfo";

        //配置请求头
        ServletOutputStream outputStream = response.getOutputStream();
        // 组装附件名称和格式
        response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");

        // 创建一个workbook 对应一个excel应用文件
        XSSFWorkbook workBook = new XSSFWorkbook();
        // 在workbook中添加一个sheet,对应Excel文件中的sheet
        XSSFSheet sheet = workBook.createSheet("表单1");


        // 构建表头
        XSSFRow headRow = ExportUtil.createRow(sheet, 0);
        XSSFCell cell;

        String[] titles = {"序号", "姓名", "性别", "申请身份","录取大类", "备注",
                "是否有入党意愿", "民族", "籍贯", "户口所在地", "出生日期",
                "手机", "qq", "身份证号", "邮箱", "生源地", "毕业高中", "高考成绩",
                "高中阶段任职/工作经历", "高中阶段所获荣誉", "T恤尺寸", "兴趣爱好", "个人评价"
        };
        int index = 0;
        for (String title : titles) {
            cell = ExportUtil.createCell(headRow, index);
            cell.setCellValue(title);
            index++;
        }

        List<Apply> list = applyService.list();

        int rowIndex = 1;
        int rowCursor = 0;
        for (Apply apply : list) {
            XSSFRow rowNumber = ExportUtil.createRow(sheet, rowIndex);
            cell = ExportUtil.createCell(rowNumber,rowCursor++);
            cell.setCellValue(apply.getUid());
            cell = ExportUtil.createCell(rowNumber,rowCursor++);
            cell.setCellValue(apply.getName());
            cell = ExportUtil.createCell(rowNumber,rowCursor++);
            cell.setCellValue(apply.getGender());
            cell = ExportUtil.createCell(rowNumber,rowCursor++);
            cell.setCellValue(apply.getIdentity());
            cell = ExportUtil.createCell(rowNumber,rowCursor++);
            cell.setCellValue(apply.getMajor());
            cell = ExportUtil.createCell(rowNumber,rowCursor++);
            cell.setCellValue(apply.getIdentityDetail());
            cell = ExportUtil.createCell(rowNumber,rowCursor++);
            cell.setCellValue(apply.getPartyWill());
            cell = ExportUtil.createCell(rowNumber,rowCursor++);
            cell.setCellValue(apply.getNation());
            cell = ExportUtil.createCell(rowNumber,rowCursor++);
            cell.setCellValue(apply.getNativePlace());
            cell = ExportUtil.createCell(rowNumber,rowCursor++);
            cell.setCellValue(apply.getHousehold());
            cell = ExportUtil.createCell(rowNumber,rowCursor++);
            cell.setCellValue(apply.getBirthDate());
            cell = ExportUtil.createCell(rowNumber,rowCursor++);
            cell.setCellValue(apply.getPhone());
            cell = ExportUtil.createCell(rowNumber,rowCursor++);
            cell.setCellValue(apply.getQq());
            cell = ExportUtil.createCell(rowNumber,rowCursor++);
            cell.setCellValue(apply.getIdcard());
            cell = ExportUtil.createCell(rowNumber,rowCursor++);
            cell.setCellValue(apply.getEmail());
            cell = ExportUtil.createCell(rowNumber,rowCursor++);
            cell.setCellValue(apply.getFromPlace());
            cell = ExportUtil.createCell(rowNumber,rowCursor++);
            cell.setCellValue(apply.getHighSchool());
            cell = ExportUtil.createCell(rowNumber,rowCursor++);
            cell.setCellValue(apply.getScore());
            cell = ExportUtil.createCell(rowNumber,rowCursor++);
            cell.setCellValue(apply.getHighSchoolExp());
            cell = ExportUtil.createCell(rowNumber,rowCursor++);
            cell.setCellValue(apply.getHighSchoolHonour());
            cell = ExportUtil.createCell(rowNumber,rowCursor++);
            cell.setCellValue(apply.getClothesSize());
            cell = ExportUtil.createCell(rowNumber,rowCursor++);
            cell.setCellValue(apply.getHobby());
            cell = ExportUtil.createCell(rowNumber,rowCursor++);
            cell.setCellValue(apply.getIntroduction());
        }


        try {
            workBook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void exportConfirmInfo(HttpServletResponse response) throws IOException {
        //文件名
        String fileName = "confirmInfo";

        //配置请求头
        ServletOutputStream outputStream = response.getOutputStream();
        // 组装附件名称和格式
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
        response.setHeader("Content-Type","application/octet-stream");

        // 创建一个workbook 对应一个excel应用文件
        XSSFWorkbook workBook = new XSSFWorkbook();
        // 在workbook中添加一个sheet,对应Excel文件中的sheet
        XSSFSheet sheet = workBook.createSheet("表单1");


        // 构建表头
        XSSFRow headRow = ExportUtil.createRow(sheet, 0);
        XSSFCell cell;

        String[] titles = {"序号", "姓名","身份证号", "是否参加", "是否购买卧具", "卧具具体信息", "是否需要接站",
                "来津方式", "来津时间", "车次", "到站车站", "陪同人数"
        };

        int index = 0;
        for (String title : titles) {
            cell = ExportUtil.createCell(headRow, index);
            cell.setCellValue(title);
            index++;
        }

        List<Confirm> list = confirmService.list();

        int rowIndex = 1;
        int rowCursor = 0;
        for (Confirm confirm : list) {
            XSSFRow rowNumber = ExportUtil.createRow(sheet, rowIndex);
            cell = ExportUtil.createCell(rowNumber,rowCursor++);
            cell.setCellValue(confirm.getUid());
            cell = ExportUtil.createCell(rowNumber,rowCursor++);
            cell.setCellValue(confirm.getName());
            cell = ExportUtil.createCell(rowNumber,rowCursor++);
            cell.setCellValue(confirm.getIdcard());
            cell = ExportUtil.createCell(rowNumber,rowCursor++);
            cell.setCellValue(confirm.getIsJoin());
            cell = ExportUtil.createCell(rowNumber,rowCursor++);
            cell.setCellValue(confirm.getBuy());
            cell = ExportUtil.createCell(rowNumber,rowCursor++);
            cell.setCellValue(confirm.getBedNeed());
            cell = ExportUtil.createCell(rowNumber,rowCursor++);
            cell.setCellValue(confirm.getIsNeedPickUp());
            cell = ExportUtil.createCell(rowNumber,rowCursor++);
            cell.setCellValue(confirm.getWayToJin());
            cell = ExportUtil.createCell(rowNumber,rowCursor++);
            cell.setCellValue(confirm.getTimeToJin());
            cell = ExportUtil.createCell(rowNumber,rowCursor++);
            cell.setCellValue(confirm.getTrainNumber());
            cell = ExportUtil.createCell(rowNumber,rowCursor++);
            cell.setCellValue(confirm.getStation());
            cell = ExportUtil.createCell(rowNumber,rowCursor++);
            cell.setCellValue(confirm.getAccompanyNumber());
        }


        try {
            workBook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void exportAttachment(HttpServletResponse response) throws IOException {

        // 先压缩
        ZipUtil zipUtil = new ZipUtil();
        zipUtil.toZip("file", response.getOutputStream(), true);

        // 组装附件名称和格式
        response.setHeader("Content-disposition", "attachment; filename=" + "attachments" + ".zip");
    }
}
