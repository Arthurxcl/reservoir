package com.henu.reservoir.controller;

import com.henu.reservoir.dao.MeasuredResultDaoMapper;
import com.henu.reservoir.domain.CutAlgoDao;
import com.henu.reservoir.domain.MeasuredResultDao;
import com.henu.reservoir.service.CutAlgoService;
import com.henu.reservoir.service.MeasuredResultService;
import com.henu.reservoir.util.ExtractMeasuredLevel;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * 实测水位文件上传
 */

@Controller
public class MeasuredLevelUploadController {
    @Autowired
    MeasuredResultService measuredResultService;

    @PostMapping(value = "/upload/measured")
    public String uploadMeasured(Model model, @RequestParam("measuredFile") MultipartFile fileUpload,
                                 @RequestParam("reservoirName") String reservoirName) throws IOException, ParseException {
        //获取文件名
        String fileName = fileUpload.getOriginalFilename();
        //获取文件后缀名
        //String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //获取项目名称
        //String path = System.getProperty("user.dir");
        //完整文件名
        //String filePath = "\\src\\main\\resources\\static\\img\\" + fileName;

        InputStream inputStream = fileUpload.getInputStream();
        FileInputStream fileInputStream = (FileInputStream) inputStream;

        //处理文件, 获得文件中所有数据
        ExtractMeasuredLevel extractMeasuredLevel = new ExtractMeasuredLevel(fileInputStream, 1);
        ArrayList<MeasuredResultDao> allResult = extractMeasuredLevel.ReadDataFromExcel();
        //存入数据库

        for (MeasuredResultDao measuredResult : allResult) {
            measuredResultService.saveMeasuredResult(measuredResult);
        }
        return "form";
        //model.addAttribute("test", test);
/*        try {
            //将图片保存到static文件夹里
            fileUpload.transferTo(new File(filePath+fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}
