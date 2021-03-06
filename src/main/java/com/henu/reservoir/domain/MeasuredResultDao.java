package com.henu.reservoir.domain;

import java.util.Date;

public class MeasuredResultDao {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column measured_result.water_level
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    private String waterLevel;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column measured_result.measured_storage
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    private String measuredStorage;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column measured_result.site_name
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    private String siteName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column measured_result.date
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    private Date date;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column measured_result.reservoir_id
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    private Integer reservoirId;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table measured_result
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public MeasuredResultDao(String waterLevel, String measuredStorage, String siteName, Date date, Integer reservoirId) {
        this.waterLevel = waterLevel;
        this.measuredStorage = measuredStorage;
        this.siteName = siteName;
        this.date = date;
        this.reservoirId = reservoirId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table measured_result
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public MeasuredResultDao() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column measured_result.water_level
     *
     * @return the value of measured_result.water_level
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public String getWaterLevel() {
        return waterLevel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column measured_result.water_level
     *
     * @param waterLevel the value for measured_result.water_level
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public void setWaterLevel(String waterLevel) {
        this.waterLevel = waterLevel == null ? null : waterLevel.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column measured_result.measured_storage
     *
     * @return the value of measured_result.measured_storage
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public String getMeasuredStorage() {
        return measuredStorage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column measured_result.measured_storage
     *
     * @param measuredStorage the value for measured_result.measured_storage
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public void setMeasuredStorage(String measuredStorage) {
        this.measuredStorage = measuredStorage == null ? null : measuredStorage.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column measured_result.site_name
     *
     * @return the value of measured_result.site_name
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public String getSiteName() {
        return siteName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column measured_result.site_name
     *
     * @param siteName the value for measured_result.site_name
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public void setSiteName(String siteName) {
        this.siteName = siteName == null ? null : siteName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column measured_result.date
     *
     * @return the value of measured_result.date
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public Date getDate() {
        return date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column measured_result.date
     *
     * @param date the value for measured_result.date
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column measured_result.reservoir_id
     *
     * @return the value of measured_result.reservoir_id
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public Integer getReservoirId() {
        return reservoirId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column measured_result.reservoir_id
     *
     * @param reservoirId the value for measured_result.reservoir_id
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public void setReservoirId(Integer reservoirId) {
        this.reservoirId = reservoirId;
    }
}