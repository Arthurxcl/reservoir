package com.henu.reservoir.service;

import com.henu.reservoir.dao.SarImgDaoMapper;
import com.henu.reservoir.domain.SarImgDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SarImgService {
    private SarImgDaoMapper SarImgDaoMapper;

    @Autowired
    private void setSarImgDaoMapper(SarImgDaoMapper SarImgDaoMapper) {
        this.SarImgDaoMapper = SarImgDaoMapper;
    }

    public List<SarImgDao> findSarImgByReservoirAndDate(SarImgDao SarImgDao){
        return SarImgDaoMapper.selectByReservoirIdAtCertainDate(SarImgDao);
    }

    public List<SarImgDao> findSarImgByReservoirAndDate(int rid, Date date){
        SarImgDao SarImgDao = new SarImgDao();
        SarImgDao.setreservoir_id(rid);
        SarImgDao.setDate(date);
        return SarImgDaoMapper.selectByReservoirIdAtCertainDate(SarImgDao);
    }
}
