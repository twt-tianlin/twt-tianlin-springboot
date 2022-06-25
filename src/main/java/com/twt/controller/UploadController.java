package com.twt.controller;

import com.twt.service.UploadService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    UploadService uploadService;


    @PostMapping("/photo")
//    @RequiresRoles("user")
    private String uploadPhoto(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        //        创建目录
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy");
        String datePath = dataFormat.format(new Date());
        File targetFile = new File("photo/"+datePath);
        if (!targetFile.exists()){
            targetFile.mkdirs();
        }

        //        文件名     photo/2022/test.jpg
        String filePath = "photo/"+datePath+"/"+multipartFile.getOriginalFilename();

        //        开始构造流并且创建文件
        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filePath));
        outputStream.write(multipartFile.getBytes());
        outputStream.flush();
        outputStream.close();
        return filePath;

    }


    @PostMapping("file")
//    @RequiresRoles("user")
    public String uploadFile(@RequestParam("file") MultipartFile multipartFile) throws Exception{
//        创建目录
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy");
        String datePath = dataFormat.format(new Date());
        File targetFile = new File("file/"+datePath);
        if (!targetFile.exists()){
            targetFile.mkdirs();
        }

//        文件名     file/2022/test.jpg
        String filePath = "file/"+datePath+"/"+multipartFile.getOriginalFilename();

//        开始构造流并且创建文件
        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filePath));
        outputStream.write(multipartFile.getBytes());
        outputStream.flush();
        outputStream.close();
        return filePath;
    }



    @PostMapping("notice")
//    @RequiresRoles("admin")
    public String uploadNoticeAttachment(@RequestParam("file") MultipartFile multipartFile) throws Exception{
//        创建目录
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy");
        String datePath = dataFormat.format(new Date());
        File targetFile = new File("notice/"+datePath);
        if (!targetFile.exists()){
            targetFile.mkdirs();
        }

//        文件名   例如：file/2022/test.jpg
        String filePath = "notice/"+datePath+"/"+multipartFile.getOriginalFilename();

//        开始构造流并且创建文件
        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filePath));
        outputStream.write(multipartFile.getBytes());
        outputStream.flush();
        outputStream.close();
        return filePath;
    }
}
