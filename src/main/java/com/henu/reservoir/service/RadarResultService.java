package com.henu.reservoir.service;

import com.henu.reservoir.dao.RadarResultDaoMapper;
import com.henu.reservoir.domain.RadarResultDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RadarResultService {
    private RadarResultDaoMapper radarResultDaoMapper;

    @Autowired
    private void setRadarResultDaoMapper(RadarResultDaoMapper radarResultDaoMapper){
        this.radarResultDaoMapper = radarResultDaoMapper;
    }

    public List<RadarResultDao> findRadarResultByReservoirId(Integer id){
        return radarResultDaoMapper.selectByReservoirId(id);
    }

    //添加RadarResult
    public void addRadarResult(RadarResultDao dao){
        radarResultDaoMapper.insert(dao);
    }
}
