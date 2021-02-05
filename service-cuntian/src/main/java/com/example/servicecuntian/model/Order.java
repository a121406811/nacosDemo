package com.example.servicecuntian.model;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class Order {
    private String seq;
    private String osa;
    private String distyName;
    private String customerCode;
    private String endCustomerName;
    private String cpn;
    private String mpn;
    private String application;
    private String endCustomerPart;
    private String orderDate;
    private BigDecimal orderQty;
//    private String dateMark;

}
