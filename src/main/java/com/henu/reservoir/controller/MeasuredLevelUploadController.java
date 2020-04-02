package com.henu.reservoir.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.henu.reservoir.dao.FittingFormulaDaoMapper;
import com.henu.reservoir.dao.MeasuredResultDaoMapper;
import com.henu.reservoir.domain.FittingFormulaDao;
import com.henu.reservoir.domain.MeasuredResultDao;
import com.henu.reservoir.service.FittingService;
import com.henu.reservoir.service.MeasuredResultService;
import com.henu.reservoir.service.ReservoirInfoService;
import com.henu.reservoir.util.CalculateByDate;
import com.henu.reservoir.util.ExtractMeasuredLevel;
import com.henu.reservoir.util.FittingFormula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 实测水位文件上传
 */

@Controller
public class MeasuredLevelUploadController {
    private MeasuredResultService measuredResultService;
    private ReservoirInfoService reservoirInfoService;
    private FittingService fittingService;

    private Integer reservoirId;

    @Autowired
    private void setServices(
            MeasuredResultService measuredResultService,
            ReservoirInfoService reservoirInfoService,
            FittingService fittingService
    ){
        this.measuredResultService = measuredResultService;
        this.reservoirInfoService = reservoirInfoService;
        this.fittingService = fittingService;
    }

    private ObjectMapper mapper = new ObjectMapper();
    private ArrayList<MeasuredResultDao> allResult = new ArrayList<>();

    @PostMapping(value = "/upload/measured")
    @ResponseBody
    public String uploadMeasured(@RequestParam("measuredFile") MultipartFile fileUpload,
                                 @RequestParam("reservoirName") String reservoirName) throws IOException, ParseException {
        InputStream inputStream = fileUpload.getInputStream();
        FileInputStream fileInputStream = (FileInputStream) inputStream;
        //根据水库名称获取水库id
        reservoirId = reservoirInfoService.findReservoirInfoByName(reservoirName).getId();
        //处理文件, 获得文件中所有数据
        ExtractMeasuredLevel extractMeasuredLevel = new ExtractMeasuredLevel(fileInputStream, reservoirId);
        allResult = extractMeasuredLevel.ReadDataFromExcel();
        inputStream.close();
        fileInputStream.close();
        //返回Json结果
        return mapper.writeValueAsString(allResult);
    }

    @GetMapping("/upload/measured/save")
    @ResponseBody
    public void saveMeasured() {
        //用户确认数据后再存入数据库
        for (int i = 0; i < allResult.size(); i++) {
            MeasuredResultDao measuredResultDao = measuredResultService.findMeasuredResultByReservoirIdAndDate(
                    reservoirId,
                    allResult.get(i).getDate()
            );
            if (measuredResultDao == null) {
                measuredResultService.saveMeasuredResult(allResult.get(i));
            }
            else {
                measuredResultService.updateMeasuredResult(allResult.get(i));
            }
        }
        fittingService.fitMeasureLevel(reservoirId);
    }
}

