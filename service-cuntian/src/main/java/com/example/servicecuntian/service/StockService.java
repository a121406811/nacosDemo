package com.example.servicecuntian.service;

import com.example.servicecuntian.model.Stock;

import java.util.List;

public interface StockService {

    List<Stock> getStocks(int startNum, int pageNum);

    int getCount();

    String getLatestDateMark();
}
