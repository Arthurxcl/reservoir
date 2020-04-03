package com.henu.reservoir.service;

import com.henu.reservoir.dao.FittingFormulaDaoMapper;
import com.henu.reservoir.domain.FittingFormulaDao;
import com.henu.reservoir.domain.MeasuredResultDao;
import com.henu.reservoir.domain.RadarResultDao;
import com.henu.reservoir.domain.WaterAreaDao;
import com.henu.reservoir.util.FittingFormula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FittingService {

    private ReservoirInfoService reservoirInfoService;
    private MeasuredResultService measuredResultService;
    private FittingFormulaDaoMapper fittingFormulaDaoMapper;
    private RadarResultService radarResultService;
    private WaterAreaService waterAreaService;

    @Autowired
    private void setServices(
            ReservoirInfoService reservoirInfoService,
            MeasuredResultService measuredResultService,
            RadarResultService radarResultService,
            FittingFormulaDaoMapper fittingFormulaDaoMapper,
            WaterAreaService waterAreaService
    ){
        this.reservoirInfoService = reservoirInfoService;
        this.measuredResultService = measuredResultService;
        this.radarResultService = radarResultService;
        this.fittingFormulaDaoMapper = fittingFormulaDaoMapper;
        this.waterAreaService = waterAreaService;
    }


    public void fitMeasureLevel(int rid){
        //使用该水库所有日期-实测水位进行拟合
        //获取所有实测水位数据
        List<MeasuredResultDao> measuredResultDaoList = measuredResultService.findMeasuredResultByReservoirId(rid);
        if (measuredResultDaoList.size()<2){
            return;
        }
        //获取最早的一天，以此作为首日
        Date firstDate = measuredResultDaoList.get(0).getDate();
        //构造数列项
        double[] x = new double[measuredResultDaoList.size()];
        double[] y = new double[measuredResultDaoList.size()];
        for (int i = 0; i<measuredResultDaoList.size(); i++){
            MeasuredResultDao item = measuredResultDaoList.get(i);
            x[i] = getDays(item.getDate(), firstDate);
            y[i] = Double.parseDouble(item.getWaterLevel());
        }
        //拟合，保存结果
        double[] result = FittingFormula.fit(x, y, 5);
        setFittingFormula(result, "实测水位模型", "measured", firstDate, rid);

    }

    public void fitRadarLevel(int rid){
        //使用该水库所有日期-实测水位进行拟合
        //获取所有实测水位数据
        List<RadarResultDao> radarResultDaoList = radarResultService.findRadarResultByReservoirId(rid);
        if (radarResultDaoList.size()<2){
            return;
        }
        //获取最早的一天，以此作为首日
        Date firstDate = radarResultDaoList.get(0).getDate();
        //构造数列项
        double[] x = new double[radarResultDaoList.size()];
        double[] y = new double[radarResultDaoList.size()];
        for (int i = 0; i<radarResultDaoList.size(); i++){
            RadarResultDao item = radarResultDaoList.get(i);
            x[i] = getDays(item.getDate(), firstDate);
            y[i] = Double.parseDouble(item.getWaterLevel());
        }
        //拟合，保存结果
        double[] result = FittingFormula.fit(x, y, 5);
        setFittingFormula(result, "遥测水位模型", "radar", firstDate, rid);

    }

    public void fitRadarLevelSarArea(int rid){
        //遥测水位+Sar面积拟合
        //获取该水库的所有sar面积数据
        List<WaterAreaDao> waterAreaDaoList = waterAreaService.findWaterAreaByReservoirId(rid, 1);
        //判断面积数据项数量是否大于1
        if (waterAreaDaoList.size()<2){
            return;
        }
        fitLevel(rid, waterAreaDaoList, "遥测水位模型", "area", "遥测+SAR");
    }

    public void fitRadarLevelOpticalArea(int rid){
        //遥测水位+光学面积拟合
        //获取该水库的所有光学面积数据
        List<WaterAreaDao> waterAreaDaoList = waterAreaService.findWaterAreaByReservoirId(rid, 2);
        //判断面积数据项数量是否大于1
        if (waterAreaDaoList.size()<2){
            return;
        }
        fitLevel(rid, waterAreaDaoList, "遥测水位模型", "area", "遥测+光学");
    }

    public void fitRadarLevelSarAndOpticalArea(int rid){
        //遥测水位+SAR+光学面积拟合
        //获取该水库的所有面积数据
        List<WaterAreaDao> waterAreaDaoList = waterAreaService.findWaterAreaByReservoirId(rid, 0);
        //判断面积数据项数量是否大于1
        if (waterAreaDaoList.size()<2){
            return;
        }
        if (waterAreaDaoList.get(0).getDate() == waterAreaDaoList.get(1).getDate()){
            return;
        }
        fitLevel(rid, waterAreaDaoList, "遥测水位模型", "area", "遥测+SAR+光学");
    }

    public void fitMeasuresLevelSarAndOpticalArea(int rid){
        //实测水位+SAR+光学面积拟合
        //获取该水库的所有面积数据
        List<WaterAreaDao> waterAreaDaoList = waterAreaService.findWaterAreaByReservoirId(rid, 0);
        //判断面积数据项数量是否大于1
        if (waterAreaDaoList.size()<2){
            return;
        }
        if (waterAreaDaoList.get(0).getDate() == waterAreaDaoList.get(1).getDate()){
            return;
        }
        fitLevel(rid, waterAreaDaoList, "实测水位模型", "area", "实测+SAR+光学");
    }

    public int update(FittingFormulaDao fittingFormulaDao){
        return fittingFormulaDaoMapper.updateByPrimaryKey(fittingFormulaDao);
    }

    private void fitLevel(
            int rid,
            List<WaterAreaDao> waterAreaDaoList,
            String levelModelName,
            String type,
            String resultModelName
    ){
        //x: 水位，y: 面积/蓄水量
        double[] x = new double[waterAreaDaoList.size()];
        double[] y = new double[waterAreaDaoList.size()];
        double[] params = new double[0];
        String paramsString = "";
        //判断遥测模型是否存在
        FittingFormulaDao levelDao = findByNameAndReservoirId(rid, levelModelName);
        boolean modelExist = (levelDao != null);
        //如果模型存在，读取模型
        if (modelExist){
            paramsString = levelDao.getOrders();
            params = getParamsByString(paramsString);
        }
        //构造数列项
        //先找记录，无记录才使用拟合
        //判断是否需要使用拟合水位
        for (int i=0; i<waterAreaDaoList.size();i++){
            WaterAreaDao waterAreaDao= waterAreaDaoList.get(i);
            //判断是否与下一条数据日期相同（当使用SAR+光学时有用）
            //如果相同，下一条的水位=（这条+下条）/2，跳过这条。
            if (i < waterAreaDaoList.size() - 1){
                WaterAreaDao waterAreaDaoNext =  waterAreaDaoList.get(i + 1);
                if (waterAreaDao.getDate() == waterAreaDaoNext.getDate()){
                    double area = (Double.parseDouble(waterAreaDao.getArea())+Double.parseDouble(waterAreaDaoNext.getArea()))/2;
                    waterAreaDaoNext.setArea(area+"");
                    continue;
                }
            }
            Date date = waterAreaDao.getDate();
            RadarResultDao radarResultDao = radarResultService.findRadarResultByReservoirIdAndDate(rid, date);
            if (radarResultDao == null){
                //需要拟合
                if (!modelExist){
                    //没有模型，无法拟合
                    return;
                }
                else{
                    //使用模型计算出水位
                    int days = getDays(date, levelDao.getFirstDate());
                    x[i] = getResultFromFittingParams(days, params);
                }
            }
            else {
                //不需要拟合，直接取数据
                x[i] = Double.parseDouble(radarResultDao.getWaterLevel());
            }
            y[i] = Double.parseDouble(waterAreaDao.getArea());
        }
        //拟合，保存结果
        double[] result = FittingFormula.fit(x, y, 5);
        setFittingFormula(result, resultModelName, type, new Date(), rid);
    }

    private void setFittingFormula(double[] fittingResult, String name, String type, Date firstDate, int rid){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i< fittingResult.length; i++){
            stringBuilder.append(fittingResult[i]);
            stringBuilder.append(",");
        }
        String orders = stringBuilder.toString();
        orders = orders.substring(0, orders.length()-1);

        //判断是否已有该模型，没有则新建，有则更新
        FittingFormulaDao fittingFormulaDao = findByNameAndReservoirId(rid, name);
        if (fittingFormulaDao==null){
            fittingFormulaDaoMapper.insert(new FittingFormulaDao(
                    name,
                    orders,
                    firstDate,
                    new Date(),
                    type,
                    rid
            ));
        }
        else {
            fittingFormulaDao.setFirstDate(firstDate);
            fittingFormulaDao.setDate(new Date());
            fittingFormulaDao.setOrders(orders);
            fittingFormulaDaoMapper.updateByPrimaryKey(fittingFormulaDao);
        }
    }

    public FittingFormulaDao findByNameAndReservoirId(int rid, String name){
        FittingFormulaDao dao = new FittingFormulaDao();
        dao.setReservoirId(rid);
        dao.setName(name);
        return fittingFormulaDaoMapper.selectByNameAndReservoirId(dao);
    }

    public List<FittingFormulaDao> findByTypeAndReservoirId(int rid, String type){
        FittingFormulaDao dao = new FittingFormulaDao();
        dao.setReservoirId(rid);
        dao.setType(type);
        return fittingFormulaDaoMapper.selectByTypeAndReservoirId(dao);
    }

    public int add(FittingFormulaDao dao){
        return fittingFormulaDaoMapper.insert(dao);
    }

    public int deleteById(int id){
        return fittingFormulaDaoMapper.deleteByPrimaryKey(id);
    }

    private double[] getParamsByString(String str){
        //  "1, 2, 3" => [1.0, 2.0, 3.0]
        String[] strings = str.split(",");
        double[] params = new double[strings.length];
        for (int i = 0; i<strings.length; i++){
            System.out.println(strings[i]);
            params[i] = Double.parseDouble(strings[i]);
        }
        return params;
    }

    private double getResultFromFittingParams(double x, double[] params){
        double result = 0;
        for (int i = 0; i < params.length; i++){
            result += params[i] * Math.pow(x, i);
        }
        return result;
    }

    private int getDays(Date date, Date firstDate){
        return (int)((date.getTime()-firstDate.getTime())/(24*60*60*1000))+1;
    }
}
