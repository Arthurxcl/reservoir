package com.henu.reservoir.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.henu.reservoir.domain.*;
import com.henu.reservoir.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ReservoirInfoController {
    //水库相关信息查询相关
    private ReservoirInfoService reservoirInfoService;
    private MeasuredResultService measuredResultService;
    private RadarResultService radarResultService;
    private WaterAreaService waterAreaService;
    private OpticalImgService opticalImgService;
    private SarImgService sarImgService;

    //idea喜欢这种写法~
    @Autowired
    private void setServices(
            ReservoirInfoService reservoirInfoService,
            MeasuredResultService measuredResultService,
            RadarResultService radarResultService,
            WaterAreaService waterAreaService,
            OpticalImgService opticalImgService,
            SarImgService sarImgService
    ){
        this.reservoirInfoService = reservoirInfoService;
        this.measuredResultService = measuredResultService;
        this.radarResultService = radarResultService;
        this.waterAreaService = waterAreaService;
        this.opticalImgService = opticalImgService;
        this.sarImgService = sarImgService;
    }

    private ObjectMapper mapper = new ObjectMapper();

    @GetMapping("api/reservoirInfo")
    @ResponseBody
    public String getInfoByReservoirName(String rname){
        //使用name获取ID
        ReservoirInfoDao reservoirInfoDao = reservoirInfoService.findReservoirInfoByName(rname);
        //使用ID查找信息

        return reservoirInfoDao.getName();
    }

    @GetMapping("api/reservoirInfo/reservoir")
    @ResponseBody
    public String getReservoir(){
        List<ReservoirInfoDao> list = reservoirInfoService.findAllReservoirInfo();
        try {
            return mapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "error";
    }

    @GetMapping("api/reservoirInfo/measuredResult")
    @ResponseBody
    public String getMeasuredResult(Integer rid) {
        //获取该水库实测水位
        List<MeasuredResultDao> list = measuredResultService.findMeasuredResultByReservoirId(rid);
        try {
            return mapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "error";
    }

    @GetMapping("api/reservoirInfo/radarResult")
    @ResponseBody
    public String getRadarResult(Integer rid) {
        //获取该水库遥测水位
        List<RadarResultDao> list = radarResultService.findRadarResultByReservoirId(rid);
        try {
            return mapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "error";
    }

    @GetMapping("api/reservoirInfo/waterArea")
    @ResponseBody
    public String getWaterArea(Integer rid, Integer method) {
        //获取该水库水域面积, method:{1-SAR, 2-光学, 3-all}
        List<WaterAreaDao> list = waterAreaService.findWaterAreaByReservoirId(rid, method);
        try {
            return mapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "error";
    }

    @GetMapping("api/reservoirInfo/optical_img")
    @ResponseBody
    public String getOpticalImage(Integer rid, String date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        try {
            Date true_date = dateFormat.parse(date);
            List<OpticalImgDao> list = opticalImgService.findOpticalImgByReservoirAndDate(rid, true_date);
            return mapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "error";
    }

    @GetMapping("api/reservoirInfo/sar_img")
    @ResponseBody
    public String getSarImage(Integer rid, String date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        try {
            Date true_date = dateFormat.parse(date);
            List<SarImgDao> list = sarImgService.findSarImgByReservoirAndDate(rid, true_date);
            return mapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "error";
    }

}
