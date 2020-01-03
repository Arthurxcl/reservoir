package com.henu.reservoir.dao;

import com.henu.reservoir.domain.SarImgDao;

import java.util.Date;
import java.util.List;

public interface SarImgDaoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Sar_img
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Sar_img
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    int insert(SarImgDao record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Sar_img
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    int insertSelective(SarImgDao record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Sar_img
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    SarImgDao selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Sar_img
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    int updateByPrimaryKeySelective(SarImgDao record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Sar_img
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    int updateByPrimaryKey(SarImgDao record);

    List<SarImgDao> selectByReservoirIdAtCertainDate(SarImgDao SarImgDao);

    //查找SAR所有图像
    List<SarImgDao> selectAll();

    //根据关键字查找SAR图像
    //likeWord = '%' + keyWord + '%'
    List<SarImgDao> selectByKeyWord(String likeWord, String keyWord);

}