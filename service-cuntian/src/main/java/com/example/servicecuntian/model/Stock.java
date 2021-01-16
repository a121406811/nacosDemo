package com.example.servicecuntian.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public class Stock implements Serializable {
    private String osa;
    private String distributorName;
    private String customerCode;
    private String endCostomerName;
    private String partNumber;
    private String mpn;
    private String application;
    private String endCustomerPart;
    private String warehouseName;
    private BigDecimal stockQty;
    private String dateformat;
    private Timestamp inhivetime;

    public String getOsa() {
        return osa;
    }

    public void setOsa(String osa) {
        this.osa = osa;
    }

    public String getDistributorName() {
        return distributorName;
    }

    public void setDistributorName(String distributorName) {
        this.distributorName = distributorName;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getEndCostomerName() {
        return endCostomerName;
    }

    public void setEndCostomerName(String endCostomerName) {
        this.endCostomerName = endCostomerName;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getMpn() {
        return mpn;
    }

    public void setMpn(String mpn) {
        this.mpn = mpn;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getEndCustomerPart() {
        return endCustomerPart;
    }

    public void setEndCustomerPart(String endCustomerPart) {
        this.endCustomerPart = endCustomerPart;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public BigDecimal getStockQty() {
        return stockQty;
    }

    public void setStockQty(BigDecimal stockQty) {
        this.stockQty = stockQty;
    }

    public String getDateformat() {
        return dateformat;
    }

    public void setDateformat(String dateformat) {
        this.dateformat = dateformat;
    }

    public Timestamp getInhivetime() {
        return inhivetime;
    }

    public void setInhivetime(Timestamp inhivetime) {
        this.inhivetime = inhivetime;
    }

    @Override
    public String toString() {
        return "XhStock{" +
                "osa='" + osa + '\'' +
                ", distributorName='" + distributorName + '\'' +
                ", customerCode='" + customerCode + '\'' +
                ", endCostomerName='" + endCostomerName + '\'' +
                ", partNumber='" + partNumber + '\'' +
                ", mpn='" + mpn + '\'' +
                ", application='" + application + '\'' +
                ", endCustomerPart='" + endCustomerPart + '\'' +
                ", warehouseName='" + warehouseName + '\'' +
                ", stockQty=" + stockQty +
                ", dateformat='" + dateformat + '\'' +
                ", inhivetime=" + inhivetime +
                '}';
    }


}
