package com.henu.reservoir.domain;

public class CutAlgoDao {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cut_algo.id
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cut_algo.name
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    private String name;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cut_algo
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public CutAlgoDao(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cut_algo
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public CutAlgoDao() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cut_algo.id
     *
     * @return the value of cut_algo.id
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cut_algo.id
     *
     * @param id the value for cut_algo.id
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cut_algo.name
     *
     * @return the value of cut_algo.name
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cut_algo.name
     *
     * @param name the value for cut_algo.name
     *
     * @mbg.generated Tue Dec 31 10:42:04 CST 2019
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}