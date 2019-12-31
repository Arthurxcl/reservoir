package com.henu.reservoir.dao;

import com.henu.reservoir.domain.RadarLevelDao;

public interface RadarLevelDaoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table radar_level
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table radar_level
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    int insert(RadarLevelDao record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table radar_level
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    int insertSelective(RadarLevelDao record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table radar_level
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    RadarLevelDao selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table radar_level
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    int updateByPrimaryKeySelective(RadarLevelDao record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table radar_level
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    int updateByPrimaryKey(RadarLevelDao record);
}