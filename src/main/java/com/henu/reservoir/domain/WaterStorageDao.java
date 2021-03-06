package com.henu.reservoir.domain;

import java.util.Date;

public class WaterStorageDao {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column water_storage.id
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column water_storage.cal_storage
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    private String calStorage;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column water_storage.model_id
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    private Integer modelId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column water_storage.measured_storage
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    private String measuredStorage;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column water_storage.site_name
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    private Date date;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column optical_img.reservoir_id
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    private Integer reservoir_id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column water_storage.sar_area
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    private String sarArea;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column water_storage.optical_area
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    private String opticalArea;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column water_storage.measured_level
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    private String measuredLevel;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column water_storage.radar_level
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    private String radarLevel;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table water_storage
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public WaterStorageDao(Integer id, String calStorage, Integer modelId, String measuredStorage, Date date, Integer reservoir_id, String sarArea, String opticalArea, String measuredLevel, String radarLevel) {
        this.id = id;
        this.calStorage = calStorage;
        this.modelId = modelId;
        this.measuredStorage = measuredStorage;
        this.date = date;
        this.reservoir_id = reservoir_id;
        this.sarArea = sarArea;
        this.opticalArea = opticalArea;
        this.measuredLevel = measuredLevel;
        this.radarLevel = radarLevel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table water_storage
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public WaterStorageDao() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column water_storage.id
     *
     * @return the value of water_storage.id
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column water_storage.id
     *
     * @param id the value for water_storage.id
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }


    public Date getDate() {
        return date;
    }

    public Integer getReservoir_id() {
        return reservoir_id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setReservoir_id(Integer reservoir_id) {
        this.reservoir_id = reservoir_id;
    }


    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column water_storage.cal_storage
     *
     * @return the value of water_storage.cal_storage
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public String getCalStorage() {
        return calStorage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column water_storage.cal_storage
     *
     * @param calStorage the value for water_storage.cal_storage
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public void setCalStorage(String calStorage) {
        this.calStorage = calStorage == null ? null : calStorage.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column water_storage.model_id
     *
     * @return the value of water_storage.model_id
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public Integer getModelId() {
        return modelId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column water_storage.model_id
     *
     * @param modelId the value for water_storage.model_id
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column water_storage.measured_storage
     *
     * @return the value of water_storage.measured_storage
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public String getMeasuredStorage() {
        return measuredStorage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column water_storage.measured_storage
     *
     * @param measuredStorage the value for water_storage.measured_storage
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public void setMeasuredStorage(String measuredStorage) {
        this.measuredStorage = measuredStorage == null ? null : measuredStorage.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column water_storage.sar_area
     *
     * @return the value of water_storage.sar_area
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public String getSarArea() {
        return sarArea;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column water_storage.sar_area
     *
     * @param sarArea the value for water_storage.sar_area
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public void setSarArea(String sarArea) {
        this.sarArea = sarArea == null ? null : sarArea.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column water_storage.optical_area
     *
     * @return the value of water_storage.optical_area
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public String getOpticalArea() {
        return opticalArea;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column water_storage.optical_area
     *
     * @param opticalArea the value for water_storage.optical_area
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public void setOpticalArea(String opticalArea) {
        this.opticalArea = opticalArea == null ? null : opticalArea.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column water_storage.measured_level
     *
     * @return the value of water_storage.measured_level
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public String getMeasuredLevel() {
        return measuredLevel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column water_storage.measured_level
     *
     * @param measuredLevel the value for water_storage.measured_level
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public void setMeasuredLevel(String measuredLevel) {
        this.measuredLevel = measuredLevel == null ? null : measuredLevel.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column water_storage.radar_level
     *
     * @return the value of water_storage.radar_level
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public String getRadarLevel() {
        return radarLevel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column water_storage.radar_level
     *
     * @param radarLevel the value for water_storage.radar_level
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public void setRadarLevel(String radarLevel) {
        this.radarLevel = radarLevel == null ? null : radarLevel.trim();
    }
}