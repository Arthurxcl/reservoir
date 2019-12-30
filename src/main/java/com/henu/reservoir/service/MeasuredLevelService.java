package com.henu.reservoir.service;

import com.henu.reservoir.dao.MeasuredLevelDaoMapper;
import com.henu.reservoir.domain.MeasuredLevelDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeasuredLevelService {
    @Autowired
    private MeasuredLevelDaoMapper measuredLevelDaoMapper;

    /**
     * 根据原始实测水位 ID,查询原始实测水位信息
     *
     * @param id
     * @return
     */
    public MeasuredLevelDao findMeasuredLevelById(Integer id) {
        return measuredLevelDaoMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增原始实测水位信息
     *
     * @param measuredLevelDao
     * @return
     */
    public Integer saveMeasuredLevel(MeasuredLevelDao measuredLevelDao) {
        return measuredLevelDaoMapper.insert(measuredLevelDao);
    }

    /**
     * 更新原始实测水位信息
     *
     * @param measuredLevelDao
     * @return
     */
    public Integer updateMeasuredLevel(MeasuredLevelDao measuredLevelDao) {
        return measuredLevelDaoMapper.updateByPrimaryKey(measuredLevelDao);
    }

    /**
     * 根据原始实测水位 ID,删除水库信息
     *
     * @param id
     * @return
     */
    public Integer deleteMeasuredLevel(Integer id) {
        return measuredLevelDaoMapper.deleteByPrimaryKey(id);
    }
}
