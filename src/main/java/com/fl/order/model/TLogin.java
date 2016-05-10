package com.fl.order.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class TLogin implements Serializable {
    private BigDecimal id;

    private String lastName;

    private static final long serialVersionUID = 1L;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}