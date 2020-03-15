package com.henu.reservoir.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;

@Controller
public class IndexController {
    @Value("${path.resource-path}")
    private String resourcePath;

    @GetMapping("/index")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/result")
    public String result(Model model) {
        return "result";
    }

    @GetMapping("/upload")
    public String form(Model model) {
        //判断上传的文件夹是否存在
        String projectPath = System.getProperty("user.dir");
        String uploadPath = projectPath + resourcePath + "static\\upload\\";
        String sarImgPath = uploadPath + "\\SARImg\\";
        String sarImgPathAfterCut = uploadPath + "\\SARAfterCut\\";
        String opticalImgPath = uploadPath + "\\opticalImg\\";
        String opticalImgPathAfterCut = uploadPath + "\\opticalAfterCut\\";
        File uploadPathDir = new File(uploadPath);
        File sarImgPathDir = new File(sarImgPath);
        File sarPathAfterCutDir = new File(sarImgPathAfterCut);
        File opticalImgPathDir = new File(opticalImgPath);
        File opticalPathAfterCutDir = new File(opticalImgPathAfterCut);
        //不存在则创建
        if (!uploadPathDir.exists()) {
            uploadPathDir.mkdir();
        }
        if(!sarImgPathDir.exists()) {
            sarImgPathDir.mkdir();
        }
        if(!sarPathAfterCutDir.exists()) {
            sarPathAfterCutDir.mkdir();
        }
        if(!opticalImgPathDir.exists()) {
            opticalImgPathDir.mkdir();
        }
        if(!opticalPathAfterCutDir.exists()) {
            opticalPathAfterCutDir.mkdir();
        }

        return "form";
    }

    @GetMapping("/tables")
    public String tables(Model model) {
        return "tables";
    }
}
