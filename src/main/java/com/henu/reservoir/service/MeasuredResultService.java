package com.henu.reservoir.service;

import com.henu.reservoir.dao.MeasuredResultDaoMapper;
import com.henu.reservoir.domain.MeasuredResultDao;
import org.springframework.beans.factory.annotation.Autowired;

public class MeasuredResultService {
    @Autowired
    private MeasuredResultDaoMapper measuredResultDaoMapper;

    public MeasuredResultDao findMeasuredResultById(Integer id) {
        return measuredResultDaoMapper.selectByPrimaryKey(id);
    }

    public Integer saveMeasuredResult(MeasuredResultDao measuredResultDao) {
        return measuredResultDaoMapper.insert(measuredResultDao);
    }

    public Integer updateMeasuredResult(MeasuredResultDao measuredResultDao) {
        return measuredResultDaoMapper.updateByPrimaryKey(measuredResultDao);
    }

    public Integer deleteMeasuredResult(Integer id) {
        return measuredResultDaoMapper.deleteByPrimaryKey(id);
    }
}
