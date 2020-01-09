package com.henu.reservoir.dao;

import com.henu.reservoir.domain.OpticalImgDao;

import java.util.Date;
import java.util.List;

public interface OpticalImgDaoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table optical_img
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table optical_img
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    int insert(OpticalImgDao record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table optical_img
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    int insertSelective(OpticalImgDao record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table optical_img
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    OpticalImgDao selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table optical_img
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    int updateByPrimaryKeySelective(OpticalImgDao record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table optical_img
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    int updateByPrimaryKey(OpticalImgDao record);

    List<OpticalImgDao> selectByReservoirIdAtCertainDate(OpticalImgDao opticalImgDao);

    //查找SAR所有图像
    List<OpticalImgDao> selectAll();

    //根据关键字查找SAR图像
    //likeWord = '%' + keyWord + '%'
    List<OpticalImgDao> selectByKeyWord(String likeWord, String keyWord);

    //根据路径获取影像id
    OpticalImgDao selectByPath(String path);
}