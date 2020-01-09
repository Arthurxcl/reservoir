package com.henu.reservoir.controller;

import com.henu.reservoir.dao.MeasuredResultDaoMapper;
import com.henu.reservoir.domain.CutAlgoDao;
import com.henu.reservoir.domain.MeasuredResultDao;
import com.henu.reservoir.service.CutAlgoService;
import com.henu.reservoir.service.MeasuredResultService;
import com.henu.reservoir.service.ReservoirInfoService;
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
    @Autowired
    ReservoirInfoService reservoirInfoService;

    @PostMapping(value = "/upload/measured")
    public String uploadMeasured(Model model, @RequestParam("measuredFile") MultipartFile fileUpload,
                                 @RequestParam("reservoirName") String reservoirName) throws IOException, ParseException {
        InputStream inputStream = fileUpload.getInputStream();
        FileInputStream fileInputStream = (FileInputStream) inputStream;
        //根据水库名称获取水库id
        Integer reservoirId = reservoirInfoService.findReservoirInfoByName(reservoirName).getId();
        //处理文件, 获得文件中所有数据
        ExtractMeasuredLevel extractMeasuredLevel = new ExtractMeasuredLevel(fileInputStream, reservoirId);
        ArrayList<MeasuredResultDao> allResult = extractMeasuredLevel.ReadDataFromExcel();

        //存入数据库
        for (MeasuredResultDao measuredResult : allResult) {
            measuredResultService.saveMeasuredResult(measuredResult);
        }

        //显示上传的文件
        model.addAttribute("allResult", allResult);

        return "showUploadMeasured";
    }
}
