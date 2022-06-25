package com.twt.controller;

import com.twt.service.DownloadService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;



@RequestMapping("/download")
@RequiresRoles("admin")
@RestController
public class DownloadController {

    @Autowired
    DownloadService downloadService;

    @GetMapping("applyInfo")
    public String exportApplyInfo(HttpServletResponse response) {
        try {
            downloadService.exportApplyInfo(response);
            return "success";
        } catch (Exception e) {
            return "failure";
        }
    }

    @GetMapping("confirmInfo")
    public String exportConfirmInfo(HttpServletResponse response) {
        try {
            downloadService.exportConfirmInfo(response);
            return "success";
        } catch (Exception e) {
            return "failure";
        }
    }

    @GetMapping("attachment")
    public String exportAttachment(HttpServletResponse response) {
        try {
            downloadService.exportAttachment(response);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "failure";
        }
    }


    @GetMapping("/applier/file")
    public ResponseEntity<InputStreamResource> downloadApplierFile(@RequestParam String filePath) throws Exception {

//        文件后缀
        String suffixName = filePath.substring(filePath.lastIndexOf("."));

        FileSystemResource fileSystemResource = new FileSystemResource(filePath);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment;filename=" + "applier" + suffixName);
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(fileSystemResource.contentLength())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(fileSystemResource.getInputStream()));
    }


    @GetMapping("/applier/photo")
    public void showApplierPhoto(@RequestParam String filePath, HttpServletResponse response) throws Exception {

//        文件后缀
        String suffixName = filePath.substring(filePath.lastIndexOf("."));

//        服务器文件
        FileInputStream in = new FileInputStream(filePath);


        //设置文件ContentType类型
        response.setContentType("image/" + suffixName);

        //设置文件头：最后一个参数是设置下载文件名
        //response.setHeader("Content-Disposition", "attachment;fileName="+fileName);
        ServletOutputStream out = response.getOutputStream();

        // 读取文件流
        int len = 0;
        byte[] buffer = new byte[1024 * 10];
        while ((len = in.read(buffer)) != -1) {
            out.write(buffer, 0, len);
        }
        out.flush();

    }

    @GetMapping("/notice/attachment")
    public void getNoticeAttachment(@RequestParam String filePath, HttpServletResponse response) throws Exception {

//        文件后缀
        String suffixName = filePath.substring(filePath.lastIndexOf("."));

//        服务器文件
        FileInputStream in = new FileInputStream(filePath);


        //设置文件ContentType类型
        response.setContentType("notice/" + suffixName);

        //设置文件头：最后一个参数是设置下载文件名
        response.setHeader("Content-Disposition", "attachment;fileName="+"attachment"+suffixName);
        ServletOutputStream out = response.getOutputStream();

        // 读取文件流
        int len = 0;
        byte[] buffer = new byte[1024 * 10];
        while ((len = in.read(buffer)) != -1) {
            out.write(buffer, 0, len);
        }
        out.flush();

    }


}
