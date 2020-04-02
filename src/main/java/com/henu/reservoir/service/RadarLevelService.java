package com.henu.reservoir.service;

import com.henu.reservoir.dao.RadarLevelDaoMapper;
import com.henu.reservoir.domain.RadarLevelDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RadarLevelService {
    private RadarLevelDaoMapper radarLevelDaoMapper;

    @Autowired
    private void setRadarLevelDaoMapper(
            RadarLevelDaoMapper radarLevelDaoMapper
    ){
        this.radarLevelDaoMapper = radarLevelDaoMapper;
    }

    //添加雷达高度计
    public void addRadarLevel(RadarLevelDao radarLevelDao){
        radarLevelDaoMapper.insert(radarLevelDao);
    }

    ////根据日期和卫星名查找雷达高度计
    public RadarLevelDao findRadarLevelByDateAndNameAndReservoirId(Date date, String satelliteName, int reservoirId){
        return radarLevelDaoMapper.selectByDateAndSatelliteNameAndReservoirId(date, satelliteName, reservoirId);
    }

    public RadarLevelDao findRadarLevelById(int id){

        System.out.println(id);
        return radarLevelDaoMapper.selectByPrimaryKey(id);
    }
}
