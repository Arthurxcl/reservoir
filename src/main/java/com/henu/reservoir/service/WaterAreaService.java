package com.henu.reservoir.service;

import com.henu.reservoir.dao.WaterAreaDaoMapper;
import com.henu.reservoir.domain.WaterAreaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WaterAreaService {
    private WaterAreaDaoMapper waterAreaDaoMapper;

    @Autowired
    private void setWaterAreaDaoMapper(WaterAreaDaoMapper waterAreaDaoMapper){
        this.waterAreaDaoMapper = waterAreaDaoMapper;
    }

    public List<WaterAreaDao> findWaterAreaByReservoirId(Integer id, Integer method){
        switch (method){
            case 1:
                return waterAreaDaoMapper.selectSarByReservoirId(id);
            case 2:
                return waterAreaDaoMapper.selectOpticalByReservoirId(id);
            default:
                return waterAreaDaoMapper.selectByReservoirId(id);
        }
    }
}
