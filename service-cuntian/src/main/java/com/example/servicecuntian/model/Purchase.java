package com.example.servicecuntian.model;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Data
public class Purchase {

    private int seq;
    private String osa;
    private String distyName;
    private String customerCode;
    private String endCustomerName;
    private String cpn;
    private String mpn;
    private String application;
    private String endCustomerPart;
    private String warehouse;
    private String purchaseDate;
    private BigDecimal purchaseQty;
    private String murataInvoiceNo;
//    private String dateformat;
//    private Timestamp inhivetime;

}
