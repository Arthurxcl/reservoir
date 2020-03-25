package com.henu.reservoir.util;

import com.henu.reservoir.dao.FittingFormulaDaoMapper;
import com.henu.reservoir.domain.FittingFormulaDao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * 根据两个日期计算出每天的水位，水域面积，蓄水量
 */
public class CalculateByDate {
    private Date startDate;
    private Date endDate;
    private Integer startDay;
    private Integer endDay;

    private FittingFormulaDao recentMeasured;
    private FittingFormulaDao recentRadar;
    private FittingFormulaDao recentSarMeasured;
    private FittingFormulaDao recentSarRadar;
    private FittingFormulaDao recentOpticalMeasured;
    private FittingFormulaDao recentOpticalRadar;

    public CalculateByDate() {

    }

    public CalculateByDate(FittingFormulaDaoMapper fittingFormulaDaoMapper) {
        recentMeasured = fittingFormulaDaoMapper.selectRecentlyByType("measured");
        recentRadar = fittingFormulaDaoMapper.selectRecentlyByType("radar");
        recentSarMeasured = fittingFormulaDaoMapper.selectRecentlyByType("sar_measured");
        recentSarRadar = fittingFormulaDaoMapper.selectRecentlyByType("sar_radar");
        recentOpticalMeasured = fittingFormulaDaoMapper.selectRecentlyByType("optical_measured");
        recentOpticalRadar = fittingFormulaDaoMapper.selectRecentlyByType("optical_radar");
    }

    public CalculateByDate(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.startDay = getDayByDate(startDate);
        this.endDay = getDayByDate(endDate);
    }

    /**
     * 判断实测水位是否和日期进行过拟合
     */
    public Boolean judgeMeasured() {
        if (recentMeasured == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 判断遥测水位是否和日期进行过拟合
     */
    public Boolean judgeRadar() {
        if (recentRadar == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 判断实测水位是否和sar面积进行过拟合
     */
    public Boolean judgeSarAndMeasured() {
        if (recentSarMeasured == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 判断遥测水位是否和sar面积进行过拟合
     */
    public Boolean judgeSarAndRadar() {
        if (recentSarRadar == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 判断实测水位是否和光学面积进行过拟合
     */
    public Boolean judgeOpticalAndMeasured() {
        if (recentOpticalMeasured == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 判断遥测水位是否和光学面积进行过拟合
     */
    public Boolean judgeOpticalAndRadar() {
        if (recentOpticalRadar == null) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<String> getAllDate() {
        ArrayList<String> dateList = new ArrayList<>();
        Calendar calendarStart = Calendar.getInstance();
        calendarStart.setTime(startDate);
        Calendar calendarEnd = Calendar.getInstance();
        calendarEnd.setTime(endDate);
        while (!calendarStart.equals(calendarEnd)) {
            dateList.add(calendarStart.get(Calendar.YEAR) + "-" + (calendarStart.get(Calendar.MONTH) + 1) + "-" + calendarStart.get(Calendar.DATE));
            calendarStart.add(Calendar.DATE, 1);
        }
        return dateList;
    }

    /**
     * 根据日期计算是一年中的第几天
     */
    public static Integer getDayByDate(Date current) {
        String str = String.format("%tj", current);
        return Integer.parseInt(str);
    }
}
    /**
     * 计算实测水位
     */
//    public Double calMeasuredLevel(Integer day) {
//        double result = recentMeasured.getFiveOrder()*Math.pow(day, 5) + recentMeasured.getFourOrder()*Math.pow(day, 4)
//                + recentMeasured.getThreeOrder()*Math.pow(day, 3) + recentMeasured.getTwoOrder()*Math.pow(day, 2)
//                + recentMeasured.getOneOrder()*day + recentMeasured.getZeroOrder();
//        return result;
//    }
//
//    /**
//     * 计算起始日期到结束日期的实测水位
//     * @return result
//     */
//    public ArrayList<Double> calPeriodMeasuredLevel() {
//        ArrayList<Double> result = new ArrayList<>();
//        for (int i = this.startDay; i < this.endDay; i++) {
//            result.add(calMeasuredLevel(i));
//        }
//        return result;
//    }
//
//    /**
//     * 计算遥测水位
//     */
//    public Double calRadarLevel(Integer day) {
//        double result = recentRadar.getFiveOrder()*Math.pow(day, 5) + recentRadar.getFourOrder()*Math.pow(day, 4)
//                + recentRadar.getThreeOrder()*Math.pow(day, 3) + recentRadar.getTwoOrder()*Math.pow(day, 2)
//                + recentRadar.getOneOrder()*day + recentRadar.getZeroOrder();
//        return result;
//    }
//
//    /**
//     * 根据日期和实测水位的关系计算实测水位，再根据实测水位和sar面积之间的关系计算sar面积
//     */
//    public Double calSarAreaByMeasured(Integer day) {
//        Double MeasuredLevel = calRadarLevel(day);
//        Double SarArea = recentSarMeasured.getFiveOrder()*Math.pow(MeasuredLevel, 5) + recentSarMeasured.getFourOrder()*Math.pow(MeasuredLevel, 4)
//                + recentSarMeasured.getThreeOrder()*Math.pow(MeasuredLevel, 3) + recentSarMeasured.getTwoOrder()*Math.pow(MeasuredLevel, 2)
//                + recentSarMeasured.getOneOrder()*MeasuredLevel + recentSarMeasured.getZeroOrder();
//        return SarArea;
//    }
//
//    /**
//     * 根据日期和遥测水位的关系计算实测水位，再根据遥测水位和sar面积之间的关系计算sar面积
//     */
//    public Double calSarAreaByRadar(Integer day) {
//        Double RadarLevel = calRadarLevel(day);
//        Double SarArea = recentSarRadar.getFiveOrder()*Math.pow(RadarLevel, 5) + recentSarRadar.getFourOrder()*Math.pow(RadarLevel, 4)
//                + recentSarRadar.getThreeOrder()*Math.pow(RadarLevel, 3) + recentSarRadar.getTwoOrder()*Math.pow(RadarLevel, 2)
//                + recentSarRadar.getOneOrder()*RadarLevel + recentSarRadar.getZeroOrder();
//        return SarArea;
//    }
//
//    /**
//     * 根据日期和实测水位的关系计算实测水位，再根据实测水位和光学面积的关系计算光学水域面积
//     */
//    public Double calOpticalByMeasured(Integer day) {
//        Double MeasuredLevel = calMeasuredLevel(day);
//        Double SarArea = recentOpticalMeasured.getFiveOrder()*Math.pow(MeasuredLevel, 5) + recentOpticalMeasured.getFourOrder()*Math.pow(MeasuredLevel, 4)
//                + recentOpticalMeasured.getThreeOrder()*Math.pow(MeasuredLevel, 3) + recentOpticalMeasured.getTwoOrder()*Math.pow(MeasuredLevel, 2)
//                + recentOpticalMeasured.getOneOrder()*MeasuredLevel + recentOpticalMeasured.getZeroOrder();
//        return SarArea;
//    }
//
//    /**
//     * 根据日期和遥测水位的关系计算遥测水位，再根据遥测水位和光学面积的关系计算光学水域面积
//     */
//    public Double calOpticalByRadar(Integer day) {
//        Double RadarLevel = calRadarLevel(day);
//        Double SarArea = recentOpticalRadar.getFiveOrder()*Math.pow(RadarLevel, 5) + recentOpticalRadar.getFourOrder()*Math.pow(RadarLevel, 4)
//                + recentOpticalRadar.getThreeOrder()*Math.pow(RadarLevel, 3) + recentOpticalRadar.getTwoOrder()*Math.pow(RadarLevel, 2)
//                + recentOpticalRadar.getOneOrder()*RadarLevel + recentOpticalRadar.getZeroOrder();
//        return SarArea;
//    }
//
//
//    /**
//     * 根据遥测水位计算水域面积
//     *//*
//    public Double calWaterAreaByRadar(Integer day) {
//        Double waterLevel = calRadarLevel(day);
//        Double result = 0.04 * Math.pow(waterLevel, 2) + 1.4 * waterLevel - 595.5;
//        return result;
//    }*/
//
//    /**
//     * 计算起始日期到结束日期的遥测水位
//     * @return result
//     */
//    public ArrayList<Double> calPeriodRadarLevel() {
//        ArrayList<Double> result = new ArrayList<>();
//        for (int i = this.startDay; i < this.endDay; i++) {
//            result.add(calRadarLevel(i));
//        }
//        return result;
//    }
//
//    /**
//     * 根据遥测水位和SAR水域面积计算蓄水量
//     * 先根据日期和水位的拟合参数获得水位，再根据水位和面积之间的拟合关系获得面积
//     */
//    public Double calStorageByRadarAndSAR(Integer day) {
//        /*Double sarMeasured = calSarAreaByMeasured(day);
//        Double sarRadar = calSarAreaByRadar(day);
//        Double sarArea = (sarMeasured + sarRadar) / 2;*/
//        //y=(0.07019/3)*x^3+(-7.68/2)*x^2+115.7*x+15, x 是水位高度
//        //根据日期获得遥测水位，根据遥测水位和拟合公式获得蓄水量
//        Double waterLevel = calRadarLevel(day);
//        Double result = (0.07019/3) * Math.pow(waterLevel, 3) + (-7.68/2) * Math.pow(waterLevel, 2) + 115.7 * waterLevel + 15;
//        return result;
//    }
//    public ArrayList<Double> calPeriodStorageByRadarAndSAR() {
//        ArrayList<Double> result = new ArrayList<>();
//        for (int i = this.startDay; i < this.endDay; i++) {
//            result.add(calStorageByRadarAndSAR(i));
//        }
//        return result;
//    }
//
//    /**
//     * 根据遥测水位和光学水域面积计算蓄水量
//     */
//    public Double calStorageByRadarAndOptical(Integer day) {
//        //根据日期获得遥测水位，根据遥测水位和拟合公式获得蓄水量
//        Double waterLevel = calRadarLevel(day);
//        Double result = (0.07019/3) * Math.pow(waterLevel, 3) + (-7.68/2) * Math.pow(waterLevel, 2) + 115.7 * waterLevel + 15;
//        return result;
//    }
//    public ArrayList<Double> calPeriodStorageByRadarAndOptical() {
//        ArrayList<Double> result = new ArrayList<>();
//        for (int i = this.startDay; i < this.endDay; i++) {
//            result.add(calStorageByRadarAndOptical(i));
//        }
//        return result;
//    }
//
//    /**
//     * 根据遥测水位和SAR水域面积和光学水域面积计算蓄水量
//     */
//    public Double calStorageByRadarSAROptical(Integer day) {
//        //根据日期获得遥测水位，根据遥测水位和拟合公式获得蓄水量
//        Double waterLevel = calRadarLevel(day);
//        Double result = (0.07019/3) * Math.pow(waterLevel, 3) + (-7.68/2) * Math.pow(waterLevel, 2) + 115.7 * waterLevel + 15;
//        return result;
//    }
//    public ArrayList<Double> calPeriodStorageByRadarSAROptical() {
//        ArrayList<Double> result = new ArrayList<>();
//        for (int i = this.startDay; i < this.endDay; i++) {
//            result.add(calStorageByRadarSAROptical(i));
//        }
//        return result;
//    }
//
//    /**
//     * 根据实测水位和SAR水域面积和光学水域面积计算蓄水量
//     */
//    public Double calStorageByMeasuredSAROptical(Integer day) {
//        //根据日期获得遥测水位，根据遥测水位和拟合公式获得蓄水量
//        Double waterLevel = calMeasuredLevel(day);
//        Double result = (0.07019/3) * Math.pow(waterLevel, 3) + (-7.68/2) * Math.pow(waterLevel, 2) + 115.7 * waterLevel + 15;
//        return result;
//    }
//    public ArrayList<Double> calPeriodStorageByMeasuredSAROptical() {
//        ArrayList<Double> result = new ArrayList<>();
//        for (int i = this.startDay; i < this.endDay; i++) {
//            result.add(calStorageByMeasuredSAROptical(i));
//        }
//        return result;
//    }
//}
