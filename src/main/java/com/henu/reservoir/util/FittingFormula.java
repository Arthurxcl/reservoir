package com.henu.reservoir.util;
import org.apache.commons.math3.fitting.PolynomialCurveFitter;
import org.apache.commons.math3.fitting.WeightedObservedPoints;

public class FittingFormula {

    public static double[] waterlevelfit(double[] x  ,double[] y ){

        WeightedObservedPoints points = new WeightedObservedPoints();
        for(int i = 0; i < x.length; i++) { //把数据点加入观察的序列
            points.add(x[i], y[i]);
        }
        PolynomialCurveFitter fitter = PolynomialCurveFitter.create(5);  //指定多项式阶数
        double[] result = fitter.fit(points.toList());  // 曲线拟合，结果保存于数组

        for(int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
        return result;
    }
}