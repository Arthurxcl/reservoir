package com.henu.reservoir.controller;

import com.henu.reservoir.domain.OpticalImgDao;
import com.henu.reservoir.service.OpticalImgService;
import com.henu.reservoir.service.ReservoirInfoService;
import fcm_java.ltycl.Sblty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;

@Controller
public class OpticalUploadController {
    @Autowired
    private OpticalImgService opticalImgService;
    @Autowired
    private ReservoirInfoService reservoirInfoService;

    @PostMapping(value = "/upload/optical")
    public String upload_SAR(Model model, @RequestParam("opticalFile") MultipartFile opticalFile, @RequestParam("reservoirName") String reservoirName,
                             @RequestParam("satelliteName")String satelliteName, @RequestParam("cycle") String cycle,
                             @RequestParam("date") Date date, @RequestParam("topLeft") String topLeft,
                             @RequestParam("lowerRight") String lowerLeft, @RequestParam("cutAlgo") String cutAlgo) {
        //获取文件名
        String fileName = opticalFile.getOriginalFilename();
        //获取文件后缀名
        //String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //获取文件前缀
        assert fileName != null;
        String prefixName = fileName.substring(0, fileName.lastIndexOf("."));
        //获取项目名称
        String projectPath = System.getProperty("user.dir");
        //完整文件名
        String filePath = projectPath + "\\src\\main\\resources\\static\\upload\\opticalImg\\" + fileName;
        //将原图像存入服务器
        try {
            //将图片保存到static文件夹里
            opticalFile.transferTo(new File(filePath));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String fileNameAfterCut = prefixName + ".png";
        //调用算法处理SAR图像
        String in = filePath;
        String out = projectPath + "\\src\\main\\resources\\static\\upload\\opticalAfterCut\\" + fileNameAfterCut;
        /*FCM fcm = null;
        try {
            fcm = new FCM();
            fcm.fcm(in, out);
        } catch (MWException e) {
            e.printStackTrace();
        }*/
        try {
            Sblty.ltycl(in, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //处理上传的数据
        //根据水库名称获取水库id
        Integer reservoir_id = reservoirInfoService.findReservoirInfoByName(reservoirName).getId();
        //分解上传的经纬度
        String topLongitude = topLeft.substring(0, topLeft.indexOf(" "));
        String topLatitude = topLeft.substring(topLeft.indexOf(" "));
        String lowerLongitude = lowerLeft.substring(0, lowerLeft.indexOf(" "));
        String lowerLatitude = lowerLeft.substring(lowerLeft.indexOf(" "));
        OpticalImgDao opticalImgDao = new OpticalImgDao(0, reservoir_id, satelliteName, date, Integer.parseInt(cycle),
                "static\\upload\\opticalAfterCut\\" + fileNameAfterCut, topLongitude,
                lowerLongitude, topLatitude, lowerLatitude, cutAlgo);
        //将处理后的影像数据存入数据库
        opticalImgService.insert(opticalImgDao);

        //将处理前和处理后的影像传到前端显示
        model.addAttribute("img_name", "opticalImg/" + fileName);
        model.addAttribute("img_name_after", "opticalAfterCut/" + fileNameAfterCut);

        return "showUploadImg";
    }
}
