package com.henu.reservoir.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 根据两个日期计算出每天的水位，水域面积，蓄水量
 */
public class CalculateByDate {
    private Date startDate;
    private Date endDate;
    private Integer startDay;
    private Integer endDay;

    public CalculateByDate() {
    }

    public CalculateByDate(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.startDay = this.getDayByDate(startDate);
        this.endDay = this.getDayByDate(endDate);
    }

    public ArrayList<String> getAllDate(){
        ArrayList<String> dateList = new ArrayList<>();
        Calendar calendarStart = Calendar.getInstance();
        calendarStart.setTime(startDate);
        Calendar calendarEnd = Calendar.getInstance();
        calendarEnd.setTime(endDate);
        while (!calendarStart.equals(calendarEnd)){
            dateList.add(calendarStart.get(Calendar.YEAR) + "-" + (calendarStart.get(Calendar.MONTH) + 1) + "-" + calendarStart.get(Calendar.DATE));
            calendarStart.add(Calendar.DATE, 1);
        }
        return dateList;
    }



    /**
     * 根据日期计算是一年中的第几天
     */
    public Integer getDayByDate(Date current) {
        String str = String.format("%tj", current);
        return Integer.parseInt(str);
    }

    /**
     * 计算实测水位
     */
    public Double calMeasuredLevel(Integer day) {
        double p1 = -1.416 * 0.00000001;
        double p2 = 8.078 * 0.000001;
        double p3 = -0.0009421;
        double p4 = -0.03026;
        double p5 = 156.9;
        double result = p1 *Math.pow(day, 4) + p2*Math.pow(day, 3) + p3*Math.pow(day, 2) + p4*day + p5;
        return result;
    }

    /**
     * 计算起始日期到结束日期的实测水位
     * @return result
     */
    public ArrayList<Double> calPeriodMeasuredLevel() {
        ArrayList<Double> result = new ArrayList<>();
        for (int i = this.startDay; i < this.endDay; i++) {
            result.add(calMeasuredLevel(i));
        }
        return result;
    }

    /**
     * 计算遥测水位
     */
    public Double calRadarLevel(Integer day) {
        Double p1 = -1.266 * 0.00000001;
        Double p2 = 6.889 * 0.000001;
        Double p3 = -0.0006524;
        Double p4 = -0.05273;
        Double p5 = 155.8;
        Double result = p1 *Math.pow(day, 4) + p2*Math.pow(day, 3) + p3*Math.pow(day, 2) + p4*day + p5;
        return result;
    }

    /**
     * 根据遥测水位计算水域面积
     */
    public Double calWaterAreaByRadar(Integer day) {
        Double waterLevel = calRadarLevel(day);
        Double result = 0.04 * Math.pow(waterLevel, 2) + 1.4 * waterLevel - 595.5;
        return result;
    }

    /**
     * 计算起始日期到结束日期的遥测水位
     * @return result
     */
    public ArrayList<Double> calPeriodRadarLevel() {
        ArrayList<Double> result = new ArrayList<>();
        for (int i = this.startDay; i < this.endDay; i++) {
            result.add(calRadarLevel(i));
        }
        return result;
    }

    /**
     * 根据遥测水位和SAR水域面积计算蓄水量
     */
    public Double calStorageByRadarAndSAR(Integer day) {
        //y=(0.07019/3)*x^3+(-7.68/2)*x^2+115.7*x+15, x 是水位高度
        //根据日期获得遥测水位，根据遥测水位和拟合公式获得蓄水量
        Double waterLevel = calRadarLevel(day);
        Double result = (0.07019/3) * Math.pow(waterLevel, 3) + (-7.68/2) * Math.pow(waterLevel, 2) + 115.7 * waterLevel + 15;
        return result;
    }
    public ArrayList<Double> calPeriodStorageByRadarAndSAR() {
        ArrayList<Double> result = new ArrayList<>();
        for (int i = this.startDay; i < this.endDay; i++) {
            result.add(calStorageByRadarAndSAR(i));
        }
        return result;
    }

    /**
     * 根据遥测水位和光学水域面积计算蓄水量
     */
    public Double calStorageByRadarAndOptical(Integer day) {
        //根据日期获得遥测水位，根据遥测水位和拟合公式获得蓄水量
        Double waterLevel = calRadarLevel(day);
        Double result = (0.07019/3) * Math.pow(waterLevel, 3) + (-7.68/2) * Math.pow(waterLevel, 2) + 115.7 * waterLevel + 15;
        return result;
    }
    public ArrayList<Double> calPeriodStorageByRadarAndOptical() {
        ArrayList<Double> result = new ArrayList<>();
        for (int i = this.startDay; i < this.endDay; i++) {
            result.add(calStorageByRadarAndOptical(i));
        }
        return result;
    }

    /**
     * 根据遥测水位和SAR水域面积和光学水域面积计算蓄水量
     */
    public Double calStorageByRadarSAROptical(Integer day) {
        //根据日期获得遥测水位，根据遥测水位和拟合公式获得蓄水量
        Double waterLevel = calRadarLevel(day);
        Double result = (0.07019/3) * Math.pow(waterLevel, 3) + (-7.68/2) * Math.pow(waterLevel, 2) + 115.7 * waterLevel + 15;
        return result;
    }
    public ArrayList<Double> calPeriodStorageByRadarSAROptical() {
        ArrayList<Double> result = new ArrayList<>();
        for (int i = this.startDay; i < this.endDay; i++) {
            result.add(calStorageByRadarSAROptical(i));
        }
        return result;
    }

    /**
     * 根据实测水位和SAR水域面积和光学水域面积计算蓄水量
     */
    public Double calStorageByMeasuredSAROptical(Integer day) {
        //根据日期获得遥测水位，根据遥测水位和拟合公式获得蓄水量
        Double waterLevel = calMeasuredLevel(day);
        Double result = (0.07019/3) * Math.pow(waterLevel, 3) + (-7.68/2) * Math.pow(waterLevel, 2) + 115.7 * waterLevel + 15;
        return result;
    }
    public ArrayList<Double> calPeriodStorageByMeasuredSAROptical() {
        ArrayList<Double> result = new ArrayList<>();
        for (int i = this.startDay; i < this.endDay; i++) {
            result.add(calStorageByMeasuredSAROptical(i));
        }
        return result;
    }
}
