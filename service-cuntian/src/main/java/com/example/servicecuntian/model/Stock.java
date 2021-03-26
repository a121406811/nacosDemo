package com.example.servicecuntian.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Data
public class Stock implements Serializable {

    private int seq;
    private String osa;
    private String distyName;
    private String customerCode;
    private String endCustomerName;
    private String cpn;
    private String mpn;
    private String application;
    private String endCustomerPart;
    private BigDecimal stockQty;
    private String warhouse;
//    private String dateformat;
//    private Timestamp inhivetime;

}
