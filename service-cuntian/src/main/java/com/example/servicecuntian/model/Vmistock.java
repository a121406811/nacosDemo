package com.example.servicecuntian.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Vmistock {

    private String osa;
    private String distributor_name;
    private String customer_code;
    private String end_costomer_name;
    private String part_number;
    private String mpn;
    private String application;
    private String end_customer_part;
    private String warehouse_name;
    private BigDecimal vmistock_qty;
    private String dateformat;
    private Timestamp inhivetime;

    @Override
    public String toString() {
        return "Vmistock{" +
                "osa='" + osa + '\'' +
                ", distributor_name='" + distributor_name + '\'' +
                ", customer_code='" + customer_code + '\'' +
                ", end_costomer_name='" + end_costomer_name + '\'' +
                ", part_number='" + part_number + '\'' +
                ", mpn='" + mpn + '\'' +
                ", application='" + application + '\'' +
                ", end_customer_part='" + end_customer_part + '\'' +
                ", warehouse_name='" + warehouse_name + '\'' +
                ", vmistock_qty=" + vmistock_qty +
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

    public String getDistributor_name() {
        return distributor_name;
    }

    public void setDistributor_name(String distributor_name) {
        this.distributor_name = distributor_name;
    }

    public String getCustomer_code() {
        return customer_code;
    }

    public void setCustomer_code(String customer_code) {
        this.customer_code = customer_code;
    }

    public String getEnd_costomer_name() {
        return end_costomer_name;
    }

    public void setEnd_costomer_name(String end_costomer_name) {
        this.end_costomer_name = end_costomer_name;
    }

    public String getPart_number() {
        return part_number;
    }

    public void setPart_number(String part_number) {
        this.part_number = part_number;
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

    public String getEnd_customer_part() {
        return end_customer_part;
    }

    public void setEnd_customer_part(String end_customer_part) {
        this.end_customer_part = end_customer_part;
    }

    public String getWarehouse_name() {
        return warehouse_name;
    }

    public void setWarehouse_name(String warehouse_name) {
        this.warehouse_name = warehouse_name;
    }

    public BigDecimal getVmistock_qty() {
        return vmistock_qty;
    }

    public void setVmistock_qty(BigDecimal vmistock_qty) {
        this.vmistock_qty = vmistock_qty;
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
