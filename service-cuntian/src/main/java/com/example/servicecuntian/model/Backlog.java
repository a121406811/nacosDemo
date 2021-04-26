package com.example.servicecuntian.model;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class Backlog {

    private int seq;
    private String osa;
    private String distyName;
    private String customerCode;
    private String endCustomerName;
    private String cpn;
    private String mpn;
    private String application;
    private String endCustomerPart;
    private BigDecimal backlogQty;

}
