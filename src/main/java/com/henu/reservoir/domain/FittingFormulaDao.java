package com.henu.reservoir.domain;

import java.util.Date;

public class FittingFormulaDao {
    private Integer id;
    private String name;
    private String orders;
    private Date date;
    private String type;
    private Integer reservoirId;

    public FittingFormulaDao(Integer id, String name, String orders, Date date, String type, Integer reservoirId) {
        this.id = id;
        this.name = name;
        this.orders = orders;
        this.date = date;
        this.type = type;
        this.reservoirId = reservoirId;
    }

}
