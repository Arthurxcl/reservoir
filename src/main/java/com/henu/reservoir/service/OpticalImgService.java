package com.henu.reservoir.service;

import com.henu.reservoir.dao.OpticalImgDaoMapper;
import com.henu.reservoir.domain.OpticalImgDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OpticalImgService {
    private OpticalImgDaoMapper opticalImgDaoMapper;

    @Autowired
    private void setOpticalImgDaoMapper(OpticalImgDaoMapper opticalImgDaoMapper) {
        this.opticalImgDaoMapper = opticalImgDaoMapper;
    }

    public List<OpticalImgDao> findOpticalImgByReservoirAndDate(OpticalImgDao opticalImgDao){
        return opticalImgDaoMapper.selectByReservoirIdAtCertainDate(opticalImgDao);
    }

    public List<OpticalImgDao> findOpticalImgByReservoirAndDate(int rid, Date date){
        OpticalImgDao opticalImgDao = new OpticalImgDao();
        opticalImgDao.setreservoir_id(rid);
        opticalImgDao.setDate(date);
        return opticalImgDaoMapper.selectByReservoirIdAtCertainDate(opticalImgDao);
    }
}
