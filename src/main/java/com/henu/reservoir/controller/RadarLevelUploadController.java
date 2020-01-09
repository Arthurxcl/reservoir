package com.henu.reservoir.controller;

import com.mathworks.toolbox.javabuilder.MWException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import Altitude_Sentinel3_A.Radar;

/**
 * 雷达高度计文件夹上传
 */

@Controller
public class RadarLevelUploadController {
    @PostMapping(value = "/upload/radar")
    @ResponseBody
    public String upload_SAR(Model model, @RequestParam("radarFile") MultipartFile[] radarFile,
                             @RequestParam("reservoirName") String reservoirName, @RequestParam("satelliteName")String satelliteName,
                             @RequestParam("cycle") String cycle, @RequestParam("date") Date date) throws MWException {
        // 获得当前时间
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        // 转换为字符串
        String formatDate = format.format(new Date());
        // 随机生成文件编号
        int random = new Random().nextInt(10000);
        //生成不重复的文件夹名称
        String uploadDirName = formatDate + Integer.toString(random);
        //判断文件夹是否存在
        String radarFilePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\upload\\radarData\\";
        File radarDataDir = new File(radarFilePath);
        if (!radarDataDir.exists()) {
            radarDataDir.mkdir();
        }
        //为上传的文件创建文件夹
        String uploadDirPath = radarFilePath + uploadDirName;
        File uploadDir = new File(uploadDirPath);
        uploadDir.mkdir();
        //将上传的文件存入文件夹
        try {
            for (MultipartFile file : radarFile) {
                String fileName = file.getOriginalFilename();
                String name = fileName.substring(fileName.lastIndexOf("/")+1);
                file.transferTo(new File(uploadDirPath + "\\" + name));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //处理上传的雷达高度计数据, 生成文本文件
        String str_in = uploadDirPath + "\\enhanced_measurement.nc";
        String out01 = uploadDirPath + "\\01.txt";
        String out20 = uploadDirPath + "\\20.txt";
        Radar radar = new Radar();
        radar.Altitude_Sentinel3_A(str_in, out01, out20, 32.93, 32.97);
        //处理文本文件


        return "test";
    }
}
