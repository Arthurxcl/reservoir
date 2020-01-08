package com.henu.reservoir.domain;

import java.util.Date;

public class OpticalImgDao {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column optical_img.id
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    private Integer id;

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
     * This field corresponds to the database column optical_img.satellite_name
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    private String satelliteName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column optical_img.date
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    private Date date;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column optical_img.cycle
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    private Integer cycle;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column optical_img.path
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    private String path;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column optical_img.topL_longitude
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    private String topl_longitude;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column optical_img.lowerR_longitude
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    private String lowerr_longitude;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column optical_img.topL_latitude
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    private String topl_latitude;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column optical_img.lowerR_latitude
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    private String lowerrLatitude;

    private String algoName;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table optical_img
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public OpticalImgDao(Integer id, Integer reservoir_id, String satelliteName, Date date, Integer cycle, String path, String topl_longitude, String lowerr_longitude, String topl_latitude, String lowerrLatitude, String algoName) {
        this.id = id;
        this.reservoir_id = reservoir_id;
        this.satelliteName = satelliteName;
        this.date = date;
        this.cycle = cycle;
        this.path = path;
        this.topl_longitude = topl_longitude;
        this.lowerr_longitude = lowerr_longitude;
        this.topl_latitude = topl_latitude;
        this.lowerrLatitude = lowerrLatitude;
        this.algoName = algoName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table optical_img
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public OpticalImgDao() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column optical_img.id
     *
     * @return the value of optical_img.id
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column optical_img.id
     *
     * @param id the value for optical_img.id
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column optical_img.reservoir_id
     *
     * @return the value of optical_img.reservoir_id
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public Integer getreservoir_id() {
        return reservoir_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column optical_img.reservoir_id
     *
     * @param reservoir_id the value for optical_img.reservoir_id
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public void setreservoir_id(Integer reservoir_id) {
        this.reservoir_id = reservoir_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column optical_img.satellite_name
     *
     * @return the value of optical_img.satellite_name
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public String getSatelliteName() {
        return satelliteName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column optical_img.satellite_name
     *
     * @param satelliteName the value for optical_img.satellite_name
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public void setSatelliteName(String satelliteName) {
        this.satelliteName = satelliteName == null ? null : satelliteName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column optical_img.date
     *
     * @return the value of optical_img.date
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public Date getDate() {
        return date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column optical_img.date
     *
     * @param date the value for optical_img.date
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column optical_img.cycle
     *
     * @return the value of optical_img.cycle
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public Integer getCycle() {
        return cycle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column optical_img.cycle
     *
     * @param cycle the value for optical_img.cycle
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public void setCycle(Integer cycle) {
        this.cycle = cycle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column optical_img.path
     *
     * @return the value of optical_img.path
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public String getPath() {
        return path;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column optical_img.path
     *
     * @param path the value for optical_img.path
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column optical_img.topL_longitude
     *
     * @return the value of optical_img.topL_longitude
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public String gettopl_longitude() {
        return topl_longitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column optical_img.topL_longitude
     *
     * @param topl_longitude the value for optical_img.topL_longitude
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public void settopl_longitude(String topl_longitude) {
        this.topl_longitude = topl_longitude == null ? null : topl_longitude.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column optical_img.lowerR_longitude
     *
     * @return the value of optical_img.lowerR_longitude
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public String getlowerr_longitude() {
        return lowerr_longitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column optical_img.lowerR_longitude
     *
     * @param lowerr_longitude the value for optical_img.lowerR_longitude
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public void setlowerr_longitude(String lowerr_longitude) {
        this.lowerr_longitude = lowerr_longitude == null ? null : lowerr_longitude.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column optical_img.topL_latitude
     *
     * @return the value of optical_img.topL_latitude
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public String gettopl_latitude() {
        return topl_latitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column optical_img.topL_latitude
     *
     * @param topl_latitude the value for optical_img.topL_latitude
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public void settopl_latitude(String topl_latitude) {
        this.topl_latitude = topl_latitude == null ? null : topl_latitude.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column optical_img.lowerR_latitude
     *
     * @return the value of optical_img.lowerR_latitude
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public String getLowerrLatitude() {
        return lowerrLatitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column optical_img.lowerR_latitude
     *
     * @param lowerrLatitude the value for optical_img.lowerR_latitude
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public void setLowerrLatitude(String lowerrLatitude) {
        this.lowerrLatitude = lowerrLatitude == null ? null : lowerrLatitude.trim();
    }

    public String getAlgoName() {
        return algoName;
    }

    public void setAlgoName(String algoName) {
        this.algoName = algoName;
    }
}