package com.fl.order.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class TBasedata implements Serializable {
    private BigDecimal fId;

    private String fCode;

    private String fName;

    private String fGroup;

    private String fIsgroup;

    private String fCreateBy;

    private String fCreateDate;

    private String fUpdateBy;

    private String fUpdateDate;

    private String fStatus;

    private BigDecimal fVersion;

    private String fStepid;

    private String fWerks;

    private String fRemark;

    private String fActive;

    private static final long serialVersionUID = 1L;

    public BigDecimal getfId() {
        return fId;
    }

    public void setfId(BigDecimal fId) {
        this.fId = fId;
    }

    public String getfCode() {
        return fCode;
    }

    public void setfCode(String fCode) {
        this.fCode = fCode;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getfGroup() {
        return fGroup;
    }

    public void setfGroup(String fGroup) {
        this.fGroup = fGroup;
    }

    public String getfIsgroup() {
        return fIsgroup;
    }

    public void setfIsgroup(String fIsgroup) {
        this.fIsgroup = fIsgroup;
    }

    public String getfCreateBy() {
        return fCreateBy;
    }

    public void setfCreateBy(String fCreateBy) {
        this.fCreateBy = fCreateBy;
    }

    public String getfCreateDate() {
        return fCreateDate;
    }

    public void setfCreateDate(String fCreateDate) {
        this.fCreateDate = fCreateDate;
    }

    public String getfUpdateBy() {
        return fUpdateBy;
    }

    public void setfUpdateBy(String fUpdateBy) {
        this.fUpdateBy = fUpdateBy;
    }

    public String getfUpdateDate() {
        return fUpdateDate;
    }

    public void setfUpdateDate(String fUpdateDate) {
        this.fUpdateDate = fUpdateDate;
    }

    public String getfStatus() {
        return fStatus;
    }

    public void setfStatus(String fStatus) {
        this.fStatus = fStatus;
    }

    public BigDecimal getfVersion() {
        return fVersion;
    }

    public void setfVersion(BigDecimal fVersion) {
        this.fVersion = fVersion;
    }

    public String getfStepid() {
        return fStepid;
    }

    public void setfStepid(String fStepid) {
        this.fStepid = fStepid;
    }

    public String getfWerks() {
        return fWerks;
    }

    public void setfWerks(String fWerks) {
        this.fWerks = fWerks;
    }

    public String getfRemark() {
        return fRemark;
    }

    public void setfRemark(String fRemark) {
        this.fRemark = fRemark;
    }

    public String getfActive() {
        return fActive;
    }

    public void setfActive(String fActive) {
        this.fActive = fActive;
    }
}