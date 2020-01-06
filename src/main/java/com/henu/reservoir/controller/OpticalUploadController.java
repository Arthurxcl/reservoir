package com.henu.reservoir.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;

@Controller
public class OpticalUploadController {
    @PostMapping(value = "/upload/optical")
    public String upload_SAR(Model model, @RequestParam("sarFile") MultipartFile sarFile, @RequestParam("reservoirName") String reservoirName,
                             @RequestParam("satelliteName")String satelliteName, @RequestParam("cycle") String cycle,
                             @RequestParam("date") Date date, @RequestParam("topLeft") String topLeft,
                             @RequestParam("lowerRight") String lowerLeft, @RequestParam("cutAlgo") String cutAlgo) {
        //处理SAR图像，调用算法

        //然后将处理后的影像数据存入数据库

        //获取文件名
        String fileName = sarFile.getOriginalFilename();
        //获取文件后缀名
        //String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //获取项目名称
        String projectPath = System.getProperty("user.dir");
        //完整文件名
        String filePath = "\\src\\main\\resources\\upload\\SARImg\\" + fileName;
        model.addAttribute("img_name", "opticalImg/" + fileName);
        model.addAttribute("img_name_after", "opticalAfterCut/" + fileName);

        try {
            //将图片保存到static文件夹里
            sarFile.transferTo(new File(projectPath+filePath));
            return "showUpload";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "showUpload";
    }
}
