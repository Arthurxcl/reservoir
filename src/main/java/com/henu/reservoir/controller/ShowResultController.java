package com.henu.reservoir.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.henu.reservoir.dao.*;
import com.henu.reservoir.domain.*;
import com.henu.reservoir.service.WaterAreaService;
import com.henu.reservoir.util.CalculateByDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class ShowResultController {
    private int reservoirId = 0;

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    WaterAreaService waterAreaService;
    @Autowired
    MeasuredResultDaoMapper measuredResultDaoMapper;
    @Autowired
    RadarResultDaoMapper radarResultDaoMapper;
    @Autowired
    FittingFormulaDaoMapper fittingFormulaDaoMapper;
    @Autowired
    WaterAreaDaoMapper waterAreaDaoMapper;

    @GetMapping("/getResult")
    @ResponseBody
    public String getResult(HttpSession session){
        Object o = session.getAttribute("reservoirId");
        if (o == null){
            return "false";
        }
        reservoirId = Integer.parseInt(o.toString());
        System.out.println(reservoirId);
        return "true";
    }

    @GetMapping("/getResultContent")
    public String getResultContent(Model model){
        if (reservoirId != 0){
            return "resultCharts";
        }
        return "";
    }

//    @PostMapping(value = "/getMeasuredLevel")
//    @ResponseBody
//    public String getMeasuredLevel(@RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate) {
//        //根据起始日期和结束日期计算每天的实测水位
//        CalculateByDate calculateByDate = new CalculateByDate(startDate, endDate);
//        ChartData chartData = new ChartData(calculateByDate.getAllDate(), calculateByDate.calPeriodMeasuredLevel());
//        try {
//            return mapper.writeValueAsString(chartData);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        return "error";
//    }
//
//    @PostMapping(value = "/getRadarLevel")
//    @ResponseBody
//    public String getRadarLevel(@RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate) {
//        //根据起始日期和结束日期计算每天的遥测水位
//        CalculateByDate calculateByDate = new CalculateByDate(startDate, endDate);
//        ChartData chartData = new ChartData(calculateByDate.getAllDate(), calculateByDate.calPeriodRadarLevel());
//        try {
//            return mapper.writeValueAsString(chartData);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        return "error";
//    }
//
//    @PostMapping(value = "/getWaterLevel")
//    @ResponseBody
//    public String getWaterLevel(@RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate) {
//        //根据起始日期和结束日期计算每天的两种水位
//        CalculateByDate calculateByDate = new CalculateByDate(startDate, endDate);
//        ChartData chartData = new ChartData(calculateByDate.getAllDate(), calculateByDate.calPeriodMeasuredLevel(), calculateByDate.calPeriodRadarLevel());
//        try {
//            return mapper.writeValueAsString(chartData);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        return "error";
//    }

//    /**
//     * 根据公式四种计算蓄水量
//     * @param startDate
//     * @param endDate
//     * @return
//     */
//    @PostMapping(value = "/getWaterStorage")
//    @ResponseBody
//    public String getWaterStorage(@RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate) {
//        CalculateByDate calculateByDate = new CalculateByDate(startDate, endDate);
//        //根据遥测水位和SAR水域面积
//        ArrayList<Double> result1 = calculateByDate.calPeriodStorageByRadarAndSAR();
//        //根据遥测水位和光学水域面积
//        ArrayList<Double> result2 = calculateByDate.calPeriodStorageByRadarAndOptical();
//        //根据遥测水位、SAR水域面积和光学水域面积
//        ArrayList<Double> result3 = calculateByDate.calPeriodStorageByRadarSAROptical();
//        //根据实测水位、SAR水域面积和光学水域面积
//        ArrayList<Double> result4 = calculateByDate.calPeriodStorageByMeasuredSAROptical();
//
//        ArrayList<String> x = calculateByDate.getAllDate();
//        ChartData chartData = new ChartData(x, result1, result2, result3, result4);
//
//        try {
//            return mapper.writeValueAsString(chartData);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        return "error";
//    }

    @GetMapping(value = "/getWaterArea")
    @ResponseBody
    public String getWaterArea(HttpSession session) {
        //获取当前水库id
        String id = (session.getAttribute("reservoirId")).toString();
        Integer reservoir_id = Integer.parseInt(id);
        //找到当前水库对应的所有SAR影像的水域面积
        List<WaterAreaDao> allSAR = waterAreaService.findWaterAreaByReservoirId(reservoir_id, 1);
        //找到当前水库对应的所有光学影像的水域面积
        List<WaterAreaDao> allOptical = waterAreaService.findWaterAreaByReservoirId(reservoir_id, 2);

        List<AreaDataItem> sarList = new ArrayList<>();
        for (int i = 0; i<allSAR.size();i++){
            sarList.add(getAreaItemFromDao(allSAR.get(i)));
        }

        List<AreaDataItem> opticalList = new ArrayList<>();
        for (int i = 0; i<allOptical.size();i++){
            opticalList.add(getAreaItemFromDao(allOptical.get(i)));
        }

        try {
            return mapper.writeValueAsString(new List[]{sarList, opticalList});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "error";
    }

    //转换Json用的数据项
    private class ChartData{
        public ArrayList<String> getX() {
            return x;
        }

        public void setX(ArrayList<String> x) {
            this.x = x;
        }


        private ArrayList<String> x;
        private ArrayList<Double> y1;

        public ArrayList<Double> getY1() {
            return y1;
        }

        public void setY1(ArrayList<Double> y1) {
            this.y1 = y1;
        }

        public ArrayList<Double> getY2() {
            return y2;
        }

        public void setY2(ArrayList<Double> y2) {
            this.y2 = y2;
        }

        public ArrayList<Double> getY3() {
            return y3;
        }

        public void setY3(ArrayList<Double> y3) {
            this.y3 = y3;
        }

        public ArrayList<Double> getY4() {
            return y4;
        }

        public void setY4(ArrayList<Double> y4) {
            this.y4 = y4;
        }

        private ArrayList<Double> y2;
        private ArrayList<Double> y3;
        private ArrayList<Double> y4;

        public ChartData(ArrayList<String> x, ArrayList<Double> y){
            this.x = x;
            this.y1 = y;
        }

        public ChartData(ArrayList<String> x, ArrayList<Double> y1, ArrayList<Double> y2){
            this.x = x;
            this.y1 = y1;
            this.y2 = y2;
        }

        public ChartData(ArrayList<String> x, ArrayList<Double> y1, ArrayList<Double> y2, ArrayList<Double> y3){
            this.x = x;
            this.y1 = y1;
            this.y2 = y2;
            this.y3 = y3;
        }

        public ChartData(ArrayList<String> x, ArrayList<Double> y1, ArrayList<Double> y2, ArrayList<Double> y3, ArrayList<Double> y4){
            this.x = x;
            this.y1 = y1;
            this.y2 = y2;
            this.y3 = y3;
            this.y4 = y4;
        }
    }
    private class AreaDataItem{
        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        private String date;
        private String area;
        public AreaDataItem(String date, String area){
            this.date = date;
            this.area = area;
        }
    }
    private AreaDataItem getAreaItemFromDao(WaterAreaDao dao){
        Calendar c = Calendar.getInstance();
        c.setTime(dao.getDate());
        String s = c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DATE);
        return new AreaDataItem(s, dao.getArea());
    }

    /*
   获得当前年份所有的实测水位数据
    */
    @GetMapping(value = "/getAllMeasuredLevel")
    @ResponseBody
    public String getAllMeasuredLevel(HttpSession session) {
        //获取最近一次拟合的实测水位参数
        FittingFormulaDao fittingFormulaDao = fittingFormulaDaoMapper.selectRecentlyByType("measured");
        //选出今年的实测水位数据
        List<MeasuredResultDao> allMeasured = measuredResultDaoMapper.selectCurrentYear();

        return "error";
    }

    /*
    获得当前年份所有的遥测水位数据
    */
    @GetMapping(value = "/getAllRadarLevel")
    @ResponseBody
    public String getAllRadarLevel(HttpSession session) {
        //获取最近一次拟合的遥测水位参数
        FittingFormulaDao fittingFormulaDao = fittingFormulaDaoMapper.selectRecentlyByType("radar");
        //选出今年的遥测水位数据
        List<RadarResultDao> allRadar = radarResultDaoMapper.selectCurrentYear();

        return "error";
    }

    /**
     * 获得当前年份所有的sar水域面积数据
     * @param session
     * @return String
     */
    @GetMapping(value = "/getAllSARArea")
    @ResponseBody
    public String getAllSARArea(HttpSession session) {
        //获取最近一次拟合的sar水域面积参数
        FittingFormulaDao fittingFormulaDao = fittingFormulaDaoMapper.selectRecentlyByType("sar");
        //选出今年的sar水域面积数据
        List<WaterAreaDao> allSarArea = waterAreaDaoMapper.selectCurrentYear(1);

        return "error";
    }

    /**
     * 获得当前年份所有的光学水域面积
     * @return String
     */
    @GetMapping(value = "/getAllOpticalArea")
    @ResponseBody
    public String getAllOpticalArea(HttpSession session) {
        //获取最近一次拟合的光学水域面积参数
        FittingFormulaDao fittingFormulaDao = fittingFormulaDaoMapper.selectRecentlyByType("optical");
        //选出今年的光学水域面积数据
        List<WaterAreaDao> allOpticalArea = waterAreaDaoMapper.selectCurrentYear(0);

        return "error";
    }
}
