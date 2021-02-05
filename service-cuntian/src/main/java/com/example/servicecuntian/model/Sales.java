package com.example.servicecuntian.model;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class Sales {

    private String seq;
    private String osa;
    private String distyName;  //  代理店名称
    private String customerCode;     //  村田客户code
    private String endCustomerName; //  最终客户的名称
    private String cpn;       //  Sanet part number
    private String mpn;             //  Murata part number   
    private String application;    //  用途（可以为空）   
    private String endCustomerPart; //  End customer part number
    private String salesDate;       //  销售日
    private BigDecimal salesQty;     //  销售数量
//    private String dateMark;
//    private Timestamp inhivetime;   //  入库时间
}
