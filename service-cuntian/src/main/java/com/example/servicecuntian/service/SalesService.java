package com.example.servicecuntian.service;

import com.example.servicecuntian.model.Sales;

import java.util.List;

public interface SalesService {

    public List<Sales> getSales(String salesDateFrom, String salesDateTo, int startNum, int pageNum);

    public int getCount();

    public String getLatestDateMark();
}
