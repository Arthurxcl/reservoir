package com.henu.reservoir.service;

import com.henu.reservoir.dao.SarImgDaoMapper;
import com.henu.reservoir.domain.SarImgDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SarImgService {
    private SarImgDaoMapper sarImgDaoMapper;

    @Autowired
    private void setSarImgDaoMapper(SarImgDaoMapper sarImgDaoMapper) {
        this.sarImgDaoMapper = sarImgDaoMapper;
    }

    public SarImgDao findSarImgById(int id){
        return sarImgDaoMapper.selectByPrimaryKey(id);
    }

    public List<SarImgDao> findSarImgByReservoirAndDate(SarImgDao sarImgDao){
        return sarImgDaoMapper.selectByReservoirIdAtCertainDate(sarImgDao);
    }

    public List<SarImgDao> findSarImgByReservoirAndDate(int rid, Date date){
        SarImgDao sarImgDao = new SarImgDao();
        sarImgDao.setreservoir_id(rid);
        sarImgDao.setDate(date);
        return sarImgDaoMapper.selectByReservoirIdAtCertainDate(sarImgDao);
    }
    public List<SarImgDao> findAllSarImg(){
        return sarImgDaoMapper.selectAll();
    }

    public List<SarImgDao> findSarImgByKeyWord(String key){
        StringBuilder builder = new StringBuilder();
        builder.append('%');
        builder.append(key);
        builder.append('%');
        return sarImgDaoMapper.selectByKeyWord(builder.toString(), key);
    }

    public Integer insert(SarImgDao sarImgDao) {
        return sarImgDaoMapper.insert(sarImgDao);
    }
}
