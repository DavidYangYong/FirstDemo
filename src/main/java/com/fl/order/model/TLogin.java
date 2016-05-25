package com.fl.order.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "T_LOGIN")
public class TLogin implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal ID;

    private String LAST_NAME;

    private static final long serialVersionUID = 1L;

    /**
     * @return ID
     */
    public BigDecimal getID() {
        return ID;
    }

    /**
     * @param ID
     */
    public void setID(BigDecimal ID) {
        this.ID = ID;
    }

    /**
     * @return LAST_NAME
     */
    public String getLAST_NAME() {
        return LAST_NAME;
    }

    /**
     * @param LAST_NAME
     */
    public void setLAST_NAME(String LAST_NAME) {
        this.LAST_NAME = LAST_NAME;
    }
}