package com.henu.reservoir.controller;

import com.henu.reservoir.util.CalculateByDate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

@Controller
public class ShowResultController {
    @PostMapping(value = "/getMeasuredLevel")
    @ResponseBody
    public String getMeasuredLevel(@RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate) {
        //根据起始日期和结束日期计算每天的实测水位
        CalculateByDate calculateByDate = new CalculateByDate(startDate, endDate);
        ArrayList<Double> result = calculateByDate.calPeriodMeasuredLevel();
        return "test";
    }

    @PostMapping(value = "/getRadarLevel")
    @ResponseBody
    public String getRadarLevel(@RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate) {
        //根据起始日期和结束日期计算每天的遥测水位
        CalculateByDate calculateByDate = new CalculateByDate(startDate, endDate);
        ArrayList<Double> result = calculateByDate.calPeriodRadarLevel();
        return "test";
    }

    /**
     * 根据公式四种计算蓄水量
     * @param startDate
     * @param endDate
     * @return
     */
    @PostMapping(value = "/getWaterStorage")
    @ResponseBody
    public String getWaterStorage(@RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate) {
        CalculateByDate calculateByDate = new CalculateByDate(startDate, endDate);
        //根据遥测水位和SAR水域面积
        ArrayList<Double> result1 = calculateByDate.calPeriodStorageByRadarAndSAR();
        //根据遥测水位和光学水域面积
        ArrayList<Double> result2 = calculateByDate.calPeriodStorageByRadarAndOptical();
        //根据遥测水位、SAR水域面积和光学水域面积
        ArrayList<Double> result3 = calculateByDate.calPeriodStorageByRadarSAROptical();
        //根据实测水位、SAR水域面积和光学水域面积
        ArrayList<Double> result4 = calculateByDate.calPeriodStorageByMeasuredSAROptical();

        return "test";
    }
}
