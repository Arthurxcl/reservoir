package com.henu.reservoir.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;

/**
 * 雷达高度计文件夹上传
 */

@Controller
public class RadarLevelUploadController {
    @PostMapping(value = "/upload/radar")
    @ResponseBody
    public String upload_SAR(Model model, @RequestParam("radarFile") MultipartFile[] radarFile,
                             @RequestParam("reservoirName") String reservoirName, @RequestParam("satelliteName")String satelliteName,
                             @RequestParam("cycle") String cycle, @RequestParam("date") Date date) {
        Integer i = radarFile.length;

        return Integer.toString(i);
    }
}
