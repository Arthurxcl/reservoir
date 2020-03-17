package com.henu.reservoir.domain;

import java.util.Date;

public class FittingFormulaDao {
    private Integer id;
    private Double fiveOrder;
    private Double fourOrder;
    private Double threeOrder;
    private Double twoOrder;
    private Double oneOrder;
    private Double zeroOrder;
    private Date date;

    public FittingFormulaDao(Double fiveOrder, Double fourOrder, Double threeOrder, Double twoOrder, Double oneOrder, Double zeroOrder, Date date) {
        this.fiveOrder = fiveOrder;
        this.fourOrder = fourOrder;
        this.threeOrder = threeOrder;
        this.twoOrder = twoOrder;
        this.oneOrder = oneOrder;
        this.zeroOrder = zeroOrder;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getFiveOrder() {
        return fiveOrder;
    }

    public void setFiveOrder(Double fiveOrder) {
        this.fiveOrder = fiveOrder;
    }

    public Double getFourOrder() {
        return fourOrder;
    }

    public void setFourOrder(Double fourOrder) {
        this.fourOrder = fourOrder;
    }

    public Double getThreeOrder() {
        return threeOrder;
    }

    public void setThreeOrder(Double threeOrder) {
        this.threeOrder = threeOrder;
    }

    public Double getTwoOrder() {
        return twoOrder;
    }

    public void setTwoOrder(Double twoOrder) {
        this.twoOrder = twoOrder;
    }

    public Double getOneOrder() {
        return oneOrder;
    }

    public void setOneOrder(Double oneOrder) {
        this.oneOrder = oneOrder;
    }

    public Double getZeroOrder() {
        return zeroOrder;
    }

    public void setZeroOrder(Double zeroOrder) {
        this.zeroOrder = zeroOrder;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
