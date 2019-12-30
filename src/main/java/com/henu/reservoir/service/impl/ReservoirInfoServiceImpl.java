package com.henu.reservoir.service.impl;

import com.henu.reservoir.dao.ReservoirInfoDaoMapper;
import com.henu.reservoir.domain.ReservoirInfoDao;
import com.henu.reservoir.service.ReservoirInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservoirInfoServiceImpl implements ReservoirInfoService {

    @Autowired
    private ReservoirInfoDaoMapper reservoirInfoDaoMapper;

    public ReservoirInfoDao findReservoirInfoById(Integer id) {
        return reservoirInfoDaoMapper.selectByPrimaryKey(id);
    }

    public Integer saveReservoirInfo(ReservoirInfoDao reservoirInfoDao) {
        return reservoirInfoDaoMapper.insert(reservoirInfoDao);
    }

    public Integer updateReservoirInfo(ReservoirInfoDao reservoirInfoDao) {
        return reservoirInfoDaoMapper.updateByPrimaryKeySelective(reservoirInfoDao);
    }

    public Integer deleteReservoirInfo(Integer id) {
        return reservoirInfoDaoMapper.deleteByPrimaryKey(id);
    }
}
