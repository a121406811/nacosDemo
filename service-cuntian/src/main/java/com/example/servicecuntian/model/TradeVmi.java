package com.example.servicecuntian.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class TradeVmi {
    private String osa;
    private String distributor_name;
    private String customer_code;
    private String end_costomer_name;
    private String part_number;
    private String mpn;
    private String application;
    private String end_customer_part;
    private String trade_date;
    private BigDecimal trade_qty;
    private String dateformat;
    private String vmi_warehouse_name;
    private Timestamp inhivetime;

    @Override
    public String toString() {
        return "TradeVmi{" +
                "osa='" + osa + '\'' +
                ", distributor_name='" + distributor_name + '\'' +
                ", customer_code='" + customer_code + '\'' +
                ", end_costomer_name='" + end_costomer_name + '\'' +
                ", part_number='" + part_number + '\'' +
                ", mpn='" + mpn + '\'' +
                ", application='" + application + '\'' +
                ", end_customer_part='" + end_customer_part + '\'' +
                ", trade_date='" + trade_date + '\'' +
                ", trade_qty=" + trade_qty +
                ", dateformat='" + dateformat + '\'' +
                ", vmi_warehouse_name='" + vmi_warehouse_name + '\'' +
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

    public String getTrade_date() {
        return trade_date;
    }

    public void setTrade_date(String trade_date) {
        this.trade_date = trade_date;
    }

    public BigDecimal getTrade_qty() {
        return trade_qty;
    }

    public void setTrade_qty(BigDecimal trade_qty) {
        this.trade_qty = trade_qty;
    }

    public String getDateformat() {
        return dateformat;
    }

    public void setDateformat(String dateformat) {
        this.dateformat = dateformat;
    }

    public String getVmi_warehouse_name() {
        return vmi_warehouse_name;
    }

    public void setVmi_warehouse_name(String vmi_warehouse_name) {
        this.vmi_warehouse_name = vmi_warehouse_name;
    }

    public Timestamp getInhivetime() {
        return inhivetime;
    }

    public void setInhivetime(Timestamp inhivetime) {
        this.inhivetime = inhivetime;
    }
}
