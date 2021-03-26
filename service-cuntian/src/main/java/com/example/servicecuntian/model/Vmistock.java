package com.example.servicecuntian.model;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class Vmistock {

    private int seq;
    private String osa;
    private String distyName;
    private String customerCode;
    private String endCustomerName;
    private String cpn;
    private String mpn;
    private String application;
    private String endCustomerPart;
    private BigDecimal vmiStockQty;
    private String warhouse;
//    private String dateformat;
//    private Timestamp inhivetime;

}
