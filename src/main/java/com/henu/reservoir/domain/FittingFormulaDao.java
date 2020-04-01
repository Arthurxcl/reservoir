package com.henu.reservoir.domain;

import java.util.Date;

public class FittingFormulaDao {
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrders() {
        return orders;
    }

    public void setOrders(String orders) {
        this.orders = orders;
    }

    public Date getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(Date firstDate) {
        this.firstDate = firstDate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getReservoirId() {
        return reservoirId;
    }

    public void setReservoirId(Integer reservoirId) {
        this.reservoirId = reservoirId;
    }

    private Integer id;
    private String name;
    private String orders;
    private Date firstDate;
    private Date date;
    private String type;
    private Integer reservoirId;

    public FittingFormulaDao(String name, String orders,Date firstDate, Date date, String type, Integer reservoirId) {
        this.id = 0;
        this.name = name;
        this.orders = orders;
        this.firstDate = firstDate;
        this.date = date;
        this.type = type;
        this.reservoirId = reservoirId;
    }

    public FittingFormulaDao(Integer id, String name, String orders,Date firstDate, Date date, String type, Integer reservoirId) {
        this.id = id;
        this.name = name;
        this.orders = orders;
        this.firstDate = firstDate;
        this.date = date;
        this.type = type;
        this.reservoirId = reservoirId;
    }

    public FittingFormulaDao(){}
}
