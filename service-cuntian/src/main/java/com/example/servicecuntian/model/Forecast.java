package com.example.servicecuntian.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Data
public class Forecast {

    private int seq;
    private String osa;
    private String distyName;
    private String customerCode;
    private String endCustomerName;
    private String cpn;
    private String mpn;
    private String application;
    private String endCustomerPart;
    private BigDecimal nForecastQty;
    private BigDecimal n1ForecastQty;
    private BigDecimal n2ForecastQty;
    private BigDecimal n3ForecastQty;
    private BigDecimal n4ForecastQty;
    private String flag;
    private String uploadDate;
//    private String dateformat;
//    private Timestamp inhivetime;

}
