package com.henu.reservoir.util;
import org.apache.commons.math3.fitting.PolynomialCurveFitter;
import org.apache.commons.math3.fitting.WeightedObservedPoints;

import java.math.BigDecimal;

public class FittingFormula {

    public static double[] waterlevelfit(double[] x  ,double[] y ){

        WeightedObservedPoints points = new WeightedObservedPoints();
        for(int i = 0; i < x.length; i++) { //把数据点加入观察的序列
            points.add(x[i], y[i]);
        }
        PolynomialCurveFitter fitter = PolynomialCurveFitter.create(5);  //指定多项式阶数
        double[] result = fitter.fit(points.toList());  // 曲线拟合，结果保存于数组

        //保留小数后5位
        //double[] result1 = new double[result.length];
        for (int i = 0; i < result.length; i++) {
            //BigDecimal b = new BigDecimal(result[i]);
            //result1[i] = b.setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue();
            System.out.println(result[i]);
        }
        return result;
    }
}