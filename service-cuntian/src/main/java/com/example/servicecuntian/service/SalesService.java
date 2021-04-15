package com.example.servicecuntian.service;

import com.example.servicecuntian.model.Sales;

import java.util.List;

public interface SalesService {

    List<Sales> getSales(String salesDateFrom, String salesDateTo, int startNum, int pageNum);

    int getCount();

    int getCountByDate(String from, String to);

    String getLatestDateMark();
}
