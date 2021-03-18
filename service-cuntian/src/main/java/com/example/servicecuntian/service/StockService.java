package com.example.servicecuntian.service;

import com.example.servicecuntian.model.Stock;

import java.util.List;

public interface StockService {

    public List<Stock> getStocks(int startNum, int pageNum);

    public int getCount();

    public String getLatestDateMark();
}
