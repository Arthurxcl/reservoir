package com.henu.reservoir.service;

import com.henu.reservoir.dao.MeasuredResultDaoMapper;
import com.henu.reservoir.domain.MeasuredResultDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
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
        return measuredResultDaoMapper.updateByReservoirIdAndDate(measuredResultDao);
    }

    public Integer deleteMeasuredResult(Integer id) {
        return measuredResultDaoMapper.deleteByPrimaryKey(id);
    }

    public List<MeasuredResultDao> findMeasuredResultByReservoirId(Integer id){
        return measuredResultDaoMapper.selectByReservoirId(id);
    }

    public List<MeasuredResultDao> findAllMeasuredResult(){
        return measuredResultDaoMapper.selectAll();
    }

    public List<MeasuredResultDao> findTodayMeasuredResultByRid(int rid){
        return measuredResultDaoMapper.selectToday(rid);
    }

    public MeasuredResultDao findMeasuredResultByReservoirIdAndDate(Integer rid, Date date) {
        MeasuredResultDao m = new MeasuredResultDao();
        m.setReservoirId(rid);
        m.setDate(date);
        return measuredResultDaoMapper.selectByReservoirIdAndDate(m);
    }
}
