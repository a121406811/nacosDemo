package com.example.servicecuntian.model;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class TradeVmi {

    private int seq;
    private String osa;
    private String distyName;
    private String customerCode;
    private String endCustomerName;
    private String cpn;
    private String mpn;
    private String application;
    private String endCustomerPart;
    private String tradeDate;
    private BigDecimal tradeQty;
//    private String dateformat;
//    private String vmi_warehouse_name;
//    private Timestamp inhivetime;

}
