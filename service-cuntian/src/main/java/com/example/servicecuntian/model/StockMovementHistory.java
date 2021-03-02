package com.example.servicecuntian.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class StockMovementHistory {

    private String seq;
    private String osa;
    private String distyName;
    private String customerCode;
    private String endCustomerName;
    private String cpn;
    private String mpn;
    private String application;
    private String endCustomerPart;
    private String moveDate;
    private BigDecimal moveQty;
    private String warehouse;
    private String flag;
//    private String dateformat;
//    private Timestamp inhivetime;

}
