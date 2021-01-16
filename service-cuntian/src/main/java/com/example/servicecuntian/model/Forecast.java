package com.example.servicecuntian.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Forecast {

    private String osa;
    private String distributor_name;
    private String customer_code;
    private String end_costomer_name;
    private String part_number;
    private String mpn;
    private String application;
    private String end_customer_part;
    private BigDecimal n_month;
    private BigDecimal n1_month;
    private BigDecimal n2_month;
    private BigDecimal n3_month;
    private BigDecimal n4_month;
    private String flag;
    private String upload_date;
    private String dateformat;
    private Timestamp inhivetime;

    @Override
    public String toString() {
        return "Forecast{" +
                "osa='" + osa + '\'' +
                ", distributor_name='" + distributor_name + '\'' +
                ", customer_code='" + customer_code + '\'' +
                ", end_costomer_name='" + end_costomer_name + '\'' +
                ", part_number='" + part_number + '\'' +
                ", mpn='" + mpn + '\'' +
                ", application='" + application + '\'' +
                ", end_customer_part='" + end_customer_part + '\'' +
                ", n_month=" + n_month +
                ", n1_month=" + n1_month +
                ", n2_month=" + n2_month +
                ", n3_month=" + n3_month +
                ", n4_month=" + n4_month +
                ", flag='" + flag + '\'' +
                ", upload_date='" + upload_date + '\'' +
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

    public BigDecimal getN_month() {
        return n_month;
    }

    public void setN_month(BigDecimal n_month) {
        this.n_month = n_month;
    }

    public BigDecimal getN1_month() {
        return n1_month;
    }

    public void setN1_month(BigDecimal n1_month) {
        this.n1_month = n1_month;
    }

    public BigDecimal getN2_month() {
        return n2_month;
    }

    public void setN2_month(BigDecimal n2_month) {
        this.n2_month = n2_month;
    }

    public BigDecimal getN3_month() {
        return n3_month;
    }

    public void setN3_month(BigDecimal n3_month) {
        this.n3_month = n3_month;
    }

    public BigDecimal getN4_month() {
        return n4_month;
    }

    public void setN4_month(BigDecimal n4_month) {
        this.n4_month = n4_month;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getUpload_date() {
        return upload_date;
    }

    public void setUpload_date(String upload_date) {
        this.upload_date = upload_date;
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
