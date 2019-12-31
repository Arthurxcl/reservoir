package com.henu.reservoir.domain;

import java.util.Date;

public class RadarLevelDao {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column radar_level.id
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column radar_level.satellite_name
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    private String satelliteName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column radar_level.date
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    private Date date;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column radar_level.cycle
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    private Integer cycle;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column radar_level.reservoir_id
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    private Integer reservoirId;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table radar_level
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public RadarLevelDao(Integer id, String satelliteName, Date date, Integer cycle, Integer reservoirId) {
        this.id = id;
        this.satelliteName = satelliteName;
        this.date = date;
        this.cycle = cycle;
        this.reservoirId = reservoirId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table radar_level
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public RadarLevelDao() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column radar_level.id
     *
     * @return the value of radar_level.id
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column radar_level.id
     *
     * @param id the value for radar_level.id
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column radar_level.satellite_name
     *
     * @return the value of radar_level.satellite_name
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public String getSatelliteName() {
        return satelliteName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column radar_level.satellite_name
     *
     * @param satelliteName the value for radar_level.satellite_name
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public void setSatelliteName(String satelliteName) {
        this.satelliteName = satelliteName == null ? null : satelliteName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column radar_level.date
     *
     * @return the value of radar_level.date
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public Date getDate() {
        return date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column radar_level.date
     *
     * @param date the value for radar_level.date
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column radar_level.cycle
     *
     * @return the value of radar_level.cycle
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public Integer getCycle() {
        return cycle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column radar_level.cycle
     *
     * @param cycle the value for radar_level.cycle
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public void setCycle(Integer cycle) {
        this.cycle = cycle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column radar_level.reservoir_id
     *
     * @return the value of radar_level.reservoir_id
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public Integer getReservoirId() {
        return reservoirId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column radar_level.reservoir_id
     *
     * @param reservoirId the value for radar_level.reservoir_id
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public void setReservoirId(Integer reservoirId) {
        this.reservoirId = reservoirId;
    }
}