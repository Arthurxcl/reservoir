package com.henu.reservoir.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
public class UploadController {
    @PostMapping("/upload")
    public String upload(MultipartFile fileUpload){
        //获取文件名
        String fileName = fileUpload.getOriginalFilename();
        //获取文件后缀名
        //String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //获取项目名称
        String path = System.getProperty("user.dir");
        //完整文件名
        String filePath = path+"\\src\\main\\resources\\static\\img\\";
        try {
            //将图片保存到static文件夹里
            fileUpload.transferTo(new File(filePath+fileName));
            return path;
        } catch (Exception e) {
            e.printStackTrace();
            return path;
        }
    }
}