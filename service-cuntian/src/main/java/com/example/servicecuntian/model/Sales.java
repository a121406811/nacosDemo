package com.example.servicecuntian.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Sales {

    private String osa;
    private String distributor_name;  //  代理店名称   
    private String customer_code;     //  村田客户code   
    private String end_costomer_name; //  最终客户的名称   
    private String part_number;       //  Sanet part number   
    private String mpn;             //  Murata part number   
    private String application;    //  用途（可以为空）   
    private String end_customer_part; //  End customer part number   
    private String sales_date;       //  销售日   
    private BigDecimal sales_qty;     //  销售数量   
    private String dateformat;  //  日期格式yyyy/MM/dd   
    private Timestamp inhivetime;   //  入库时间

    @Override
    public String toString() {
        return "Sales{" +
                "osa='" + osa + '\'' +
                ", distributor_name='" + distributor_name + '\'' +
                ", customer_code='" + customer_code + '\'' +
                ", end_costomer_name='" + end_costomer_name + '\'' +
                ", part_number='" + part_number + '\'' +
                ", mpn='" + mpn + '\'' +
                ", application='" + application + '\'' +
                ", end_customer_part='" + end_customer_part + '\'' +
                ", sales_date='" + sales_date + '\'' +
                ", sales_qty=" + sales_qty +
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

    public String getSales_date() {
        return sales_date;
    }

    public void setSales_date(String sales_date) {
        this.sales_date = sales_date;
    }

    public BigDecimal getSales_qty() {
        return sales_qty;
    }

    public void setSales_qty(BigDecimal sales_qty) {
        this.sales_qty = sales_qty;
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
