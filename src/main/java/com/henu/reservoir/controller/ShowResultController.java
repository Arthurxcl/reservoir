package com.henu.reservoir.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.henu.reservoir.domain.*;
import com.henu.reservoir.service.FittingService;
import com.henu.reservoir.service.MeasuredResultService;
import com.henu.reservoir.service.RadarResultService;
import com.henu.reservoir.service.WaterAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
public class ShowResultController {
    private int reservoirId = 0;

    private ObjectMapper mapper = new ObjectMapper();

    private WaterAreaService waterAreaService;
    private MeasuredResultService measuredResultService;
    private RadarResultService radarResultService;
    private FittingService fittingService;

    @Autowired
    private void setService(
            WaterAreaService waterAreaService,
            MeasuredResultService measuredResultService,
            RadarResultService radarResultService,
            FittingService fittingService
    ){
        this.waterAreaService = waterAreaService;
        this.measuredResultService = measuredResultService;
        this.radarResultService = radarResultService;
        this.fittingService = fittingService;
    }

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

    @GetMapping("/api/getChartsData")
    @ResponseBody
    public String getChartsData(HttpSession session){
        //String id = (session.getAttribute("reservoirId")).toString();
        //Integer rid = Integer.parseInt(id);
        Integer rid = 3;
        ChartsData chartsData = new ChartsData();
        chartsData.setMeasuredResult(measuredResultService.findMeasuredResultByReservoirId(rid));
        chartsData.setRadarResult(radarResultService.findRadarResultByReservoirId(rid));
        chartsData.setMeasuredModel(fittingService.findByNameAndReservoirId(rid, "实测水位模型"));
        chartsData.setRadarModel(fittingService.findByNameAndReservoirId(rid, "遥测水位模型"));
        chartsData.setLevelModel(fittingService.findByTypeAndReservoirId(rid, "level"));
        chartsData.setAreaModel(fittingService.findByTypeAndReservoirId(rid, "area"));
        chartsData.setStorageModel(fittingService.findByTypeAndReservoirId(rid, "storage"));
        try {
            return mapper.writeValueAsString(chartsData);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "error";
    }

    //转换Json用的数据项
    private class ChartsData{
        public List<MeasuredResultDao> getMeasuredResult() {
            return measuredResult;
        }

        public void setMeasuredResult(List<MeasuredResultDao> measuredResult) {
            this.measuredResult = measuredResult;
        }

        public List<RadarResultDao> getRadarResult() {
            return radarResult;
        }

        public void setRadarResult(List<RadarResultDao> radarResult) {
            this.radarResult = radarResult;
        }

        public FittingFormulaDao getMeasuredModel() {
            return measuredModel;
        }

        public void setMeasuredModel(FittingFormulaDao measuredModel) {
            this.measuredModel = measuredModel;
        }

        public FittingFormulaDao getRadarModel() {
            return radarModel;
        }

        public void setRadarModel(FittingFormulaDao radarModel) {
            this.radarModel = radarModel;
        }

        public List<FittingFormulaDao> getLevelModel() {
            return levelModel;
        }

        public void setLevelModel(List<FittingFormulaDao> levelModel) {
            this.levelModel = levelModel;
        }

        public List<FittingFormulaDao> getAreaModel() {
            return areaModel;
        }

        public void setAreaModel(List<FittingFormulaDao> areaModel) {
            this.areaModel = areaModel;
        }

        public List<FittingFormulaDao> getStorageModel() {
            return storageModel;
        }

        public void setStorageModel(List<FittingFormulaDao> storageModel) {
            this.storageModel = storageModel;
        }

        private List<MeasuredResultDao> measuredResult;
        private List<RadarResultDao> radarResult;
        private FittingFormulaDao measuredModel;
        private FittingFormulaDao radarModel;
        private List<FittingFormulaDao> levelModel;
        private List<FittingFormulaDao> areaModel;
        private List<FittingFormulaDao> storageModel;

        public ChartsData(
                List<MeasuredResultDao> measuredResult,
                List<RadarResultDao> radarResult,
                FittingFormulaDao measuredModel,
                FittingFormulaDao radarModel,
                List<FittingFormulaDao> levelModel,
                List<FittingFormulaDao> areaModel,
                List<FittingFormulaDao> storageModel
        ){
            this.measuredResult = measuredResult;
            this.radarResult = radarResult;
            this.measuredModel = measuredModel;
            this.radarModel = radarModel;
            this.levelModel = levelModel;
            this.areaModel = areaModel;
            this.storageModel = storageModel;
        }
        public ChartsData(){}
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
}
