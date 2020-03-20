package com.henu.reservoir.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.henu.reservoir.dao.FittingFormulaDaoMapper;
import com.henu.reservoir.dao.MeasuredResultDaoMapper;
import com.henu.reservoir.domain.FittingFormulaDao;
import com.henu.reservoir.domain.MeasuredResultDao;
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
    @Autowired
    MeasuredResultService measuredResultService;
    @Autowired
    ReservoirInfoService reservoirInfoService;
    @Autowired
    FittingFormulaDaoMapper fittingFormulaDaoMapper;
    @Autowired
    MeasuredResultDaoMapper measuredResultDaoMapper;

    private ObjectMapper mapper = new ObjectMapper();
    private ArrayList<MeasuredResultDao> allResult = new ArrayList<>();

    @PostMapping(value = "/upload/measured")
    @ResponseBody
    public String uploadMeasured(Model model, @RequestParam("measuredFile") MultipartFile fileUpload,
                                 @RequestParam("reservoirName") String reservoirName) throws IOException, ParseException {
        InputStream inputStream = fileUpload.getInputStream();
        FileInputStream fileInputStream = (FileInputStream) inputStream;
        //根据水库名称获取水库id
        Integer reservoirId = reservoirInfoService.findReservoirInfoByName(reservoirName).getId();
        //处理文件, 获得文件中所有数据
        ExtractMeasuredLevel extractMeasuredLevel = new ExtractMeasuredLevel(fileInputStream, reservoirId);
        allResult = extractMeasuredLevel.ReadDataFromExcel();

        //先不存入数据库
        /*
        for (MeasuredResultDao measuredResult : allResult) {
            measuredResultService.saveMeasuredResult(measuredResult);
        }

         */

        //返回Json结果
        return mapper.writeValueAsString(allResult);
    }

    @GetMapping("/upload/measured/save")
    @ResponseBody
    public void saveMeasured(){
        //用户确认数据后再存入数据库
        for (int i = 0; i < allResult.size(); i++) {
            measuredResultService.saveMeasuredResult(allResult.get(i));
        }
        //获取今年的所有实测水位数据
        List<MeasuredResultDao> allMeasured = measuredResultDaoMapper.selectCurrentYear();
        //拟合
        getDateAndMeasuredFittingResult(allMeasured);
    }

    /**
     * 获得日期和实测水位的拟合关系
     */
    public void getDateAndMeasuredFittingResult(List<MeasuredResultDao> allMeasured) {
        //设置拟合所需数组
        double[] x = new double[allMeasured.size()];
        double[] y = new double[allMeasured.size()];
        //如果数据个数大于1，则进行拟合
        if(allMeasured.size() > 1) {
            for (int i = 0; i < allMeasured.size(); i++) {
                //提取日期和水位
                x[i] = CalculateByDate.getDayByDate(allMeasured.get(i).getDate());
                y[i] = Double.parseDouble(allMeasured.get(i).getWaterLevel());
            }
            //开始拟合
            double[] result = FittingFormula.waterlevelfit(x, y);
            //将拟合结果存储在数据库中
            Date date = new Date();
            FittingFormulaDao fittingFormulaDao = new FittingFormulaDao(0, result[0], result[1], result[2], result[3], result[4], result[5], date, "measured");
            fittingFormulaDaoMapper.insert(fittingFormulaDao);
        }
    }

    /**
     * 获得日期和实测蓄水量拟合关系
     */
    public void getDateAndMeasuredStorageFittingResult(List<MeasuredResultDao> allMeasured) {
        //设置拟合所需数组
        double[] x = new double[allMeasured.size()];
        double[] y = new double[allMeasured.size()];
        //如果数据个数大于1，则进行拟合
        if(allMeasured.size() > 1) {
            for (int i = 0; i < allMeasured.size(); i++) {
                //提取日期和水位
                x[i] = CalculateByDate.getDayByDate(allMeasured.get(i).getDate());
                y[i] = Double.parseDouble(allMeasured.get(i).getMeasuredStorage());
            }
            //开始拟合
            double[] result = FittingFormula.waterlevelfit(x, y);
            //将拟合结果存储在数据库中
            Date date = new Date();
            FittingFormulaDao fittingFormulaDao = new FittingFormulaDao(0, result[0], result[1], result[2], result[3], result[4], result[5], date, "measuredStorage");
            fittingFormulaDaoMapper.insert(fittingFormulaDao);
        }
    }

    /**
     * 获得实测水位和实测蓄水量拟合关系
     */
    public void getMeasuredLevelAndMeasuredStorageFitting(List<MeasuredResultDao> allMeasured) {
        //设置拟合所需数组
        double[] x = new double[allMeasured.size()];
        double[] y = new double[allMeasured.size()];
        //如果数据个数大于1，则进行拟合
        if(allMeasured.size() > 1) {
            for (int i = 0; i < allMeasured.size(); i++) {
                //提取水位和蓄水量
                x[i] = Double.parseDouble(allMeasured.get(i).getWaterLevel());
                y[i] = Double.parseDouble(allMeasured.get(i).getMeasuredStorage());
            }
            //开始拟合
            double[] result = FittingFormula.waterlevelfit(x, y);
            //将拟合结果存储在数据库中
            Date date = new Date();
            FittingFormulaDao fittingFormulaDao = new FittingFormulaDao(0, result[0], result[1], result[2], result[3], result[4], result[5], date, "measuredStorage_measuredLevel");
            fittingFormulaDaoMapper.insert(fittingFormulaDao);
        }
    }

    /**
     * 前端请求当前年份的实测水位和实测蓄水量数据
     * @return String
     */
    @GetMapping(value = "/getCurrentMeasured")
    @ResponseBody
    public String getCurrentMeasured() {
        //获取今年的实测水位数据
        List<MeasuredResultDao> allMeasured = measuredResultDaoMapper.selectCurrentYear();
        ObjectMapper mapper = new ObjectMapper();
        //转换成json返回
        try {
            return mapper.writeValueAsString(allMeasured);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "error";
    }
}
