package com.example.servicecuntian.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public class Purchase {

    private String osa;
    private String distributorName;
    private String customerCode;
    private String endCostomerName;
    private String partNumber;
    private String mpn;
    private String application;
    private String endCustomerPart;
    private String warehouseName;
    private String purchaseDate;
    private BigDecimal stockQty;
    private String murataInvoice;
    private String dateformat;
    private Timestamp inhivetime;

    @Override
    public String toString() {
        return "Purchase{" +
                "osa='" + osa + '\'' +
                ", distributorName='" + distributorName + '\'' +
                ", customerCode='" + customerCode + '\'' +
                ", endCostomerName='" + endCostomerName + '\'' +
                ", partNumber='" + partNumber + '\'' +
                ", mpn='" + mpn + '\'' +
                ", application='" + application + '\'' +
                ", endCustomerPart='" + endCustomerPart + '\'' +
                ", warehouseName='" + warehouseName + '\'' +
                ", purchaseDate='" + purchaseDate + '\'' +
                ", stockQty=" + stockQty +
                ", murataInvoice='" + murataInvoice + '\'' +
                ", dateformat='" + dateformat + '\'' +
                ", inhivetime=" + inhivetime +
                '}';
    }

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

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public BigDecimal getStockQty() {
        return stockQty;
    }

    public void setStockQty(BigDecimal stockQty) {
        this.stockQty = stockQty;
    }

    public String getMurataInvoice() {
        return murataInvoice;
    }

    public void setMurataInvoice(String murataInvoice) {
        this.murataInvoice = murataInvoice;
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
}
