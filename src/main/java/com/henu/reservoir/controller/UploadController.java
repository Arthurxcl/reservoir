package com.henu.reservoir.controller;

import com.henu.reservoir.domain.CutAlgoDao;
import com.henu.reservoir.service.CutAlgoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Controller
public class UploadController {
    @Autowired
    private CutAlgoService cutAlgoService;
    //@PostMapping("/upload")
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

    @GetMapping("/upload")
    public String uploadInit(Model model){
        //读取算法列表，填入表单算法选择位置
        StringBuilder builder = new StringBuilder();
        for (CutAlgoDao dao: cutAlgoService.getAll()) {
            builder.append("<option value=\"" + dao.getId() + "\">");
            builder.append(dao.getName());
            builder.append("</option>");
        }
        model.addAttribute("algoList", builder.toString());
        return "upload";
    }

    @PostMapping("/upload/sar")
    @ResponseBody
    public void upload_SAR(@RequestParam("sar_file")MultipartFile sar_file, String satellite_name, String date, String cycle, String topL_longitude, String topL_latitude, String lowerR_longitude, String lowerR_latitude, String algo){
        //处理SAR图像，然后将相关数据存入数据库
        System.out.println("Filename: " + sar_file.getOriginalFilename());
        System.out.println("satellite_name: " + satellite_name);
        System.out.println("date: " + date);
        System.out.println("cycle: " + cycle);
        System.out.println("topL_longitude: " + topL_longitude);
        System.out.println("topL_latitude: " + topL_latitude);
        System.out.println("lowerR_longitude: " + lowerR_longitude);
        System.out.println("lowerR_latitude: " + lowerR_latitude);
        System.out.println("algo: " + algo);

        /*
        String fileName = sar_file.getOriginalFilename();
        //获取文件后缀名
        //String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //获取项目名称
        String path = System.getProperty("user.dir");
        //完整文件名
        String filePath = path+"\\src\\main\\resources\\static\\img\\";
        try {
            //将图片保存到static文件夹里
            sar_file.transferTo(new File(filePath+fileName));
            if (cutAlgoService.process(path, Integer.parseInt(algo))){
                //save
            }
            else {
                //fail
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}