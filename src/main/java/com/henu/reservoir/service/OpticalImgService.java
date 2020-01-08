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

    public OpticalImgDao findOpticalImgById(int id){
        return opticalImgDaoMapper.selectByPrimaryKey(id);
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

    public List<OpticalImgDao> findAllOpticalImg(){
        return opticalImgDaoMapper.selectAll();
    }

    public List<OpticalImgDao> findOpticalImgByKeyWord(String key){
        StringBuilder builder = new StringBuilder();
        builder.append('%');
        builder.append(key);
        builder.append('%');
        return opticalImgDaoMapper.selectByKeyWord(builder.toString(), key);
    }

    public Integer insert(OpticalImgDao opticalImgDao) {
        return opticalImgDaoMapper.insert(opticalImgDao);
    }
}
