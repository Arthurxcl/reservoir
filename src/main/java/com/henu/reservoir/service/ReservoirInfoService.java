package com.henu.reservoir.service;

import org.apache.ibatis.annotations.Param;
import com.henu.reservoir.domain.ReservoirInfoDao;

import java.util.List;

public interface ReservoirInfoService {
    /**
     * 根据水库 ID,查询水库信息
     *
     * @param id
     * @return
     */
    ReservoirInfoDao findReservoirInfoById(Integer id);

    /**
     * 新增水库信息
     *
     * @param reservoirInfo
     * @return
     */
    Integer saveReservoirInfo(ReservoirInfoDao reservoirInfo);

    /**
     * 更新水库信息
     *
     * @param reservoirInfo
     * @return
     */
    Integer updateReservoirInfo(ReservoirInfoDao reservoirInfo);

    /**
     * 根据水库 ID,删除水库信息
     *
     * @param id
     * @return
     */
    Integer deleteReservoirInfo(Integer id);
}
