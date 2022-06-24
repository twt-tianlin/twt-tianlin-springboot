package com.twt.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class UploadService {

    @Value("${file.uploadFolder}")
    private String uploadFolder;

    @Value("${file.staticPath}")
    private String staticPath;


    public String uploadImg(MultipartFile multipartFile){

        try {
            //日期目录
            SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy/MM/dd");
            String dataPath = dataFormat.format(new Date());
            File targetFile = new File(uploadFolder,dataPath);
            if (!targetFile.exists()){
                targetFile.mkdirs();
            }

            // 获取新文件名 随机
            String realFileName = multipartFile.getOriginalFilename();
            String imgSuffix = realFileName.substring(realFileName.lastIndexOf("."));
            String newFileName = UUID.randomUUID()+imgSuffix;


            // 此处注意
            File targetFileName = new File(targetFile,newFileName);
            multipartFile.transferTo(targetFileName);

            //可访问的路径返回给页面
            String filename = "/"+dataPath+"/"+newFileName;
            return filename;
        } catch (IOException e) {
            e.printStackTrace();
            return "fail";
        }
    }
}
