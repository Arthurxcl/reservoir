package com.henu.reservoir.service;

import com.henu.reservoir.domain.MeasuredResultDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FittingService {

    private ReservoirInfoService reservoirInfoService;
    private MeasuredResultService measuredResultService;

    @Autowired
    private void setServices(
            ReservoirInfoService reservoirInfoService,
            MeasuredResultService measuredResultService
    ){
        this.reservoirInfoService = reservoirInfoService;
        this.measuredResultService = measuredResultService;
    }


    public void FitMeasureLevel(int rid){
        //使用该水库所有日期-实测水位进行拟合
        //获取所有实测水位数据
        List<MeasuredResultDao> measuredResultDaoList = measuredResultService.findMeasuredResultByReservoirId(rid);
        //获取最早的一天，以此作为首日
        for(MeasuredResultDao item: measuredResultDaoList){
            System.out.println(item.getDate());
        }
        //构造数列项

        //拟合，保存结果

    }
}
