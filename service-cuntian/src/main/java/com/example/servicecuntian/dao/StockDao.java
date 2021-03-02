package com.example.servicecuntian.dao;

import com.example.servicecuntian.model.Stock;

import java.util.List;

public interface StockDao {

    public List<Stock> getStocks(int startNum, int pageNum);

    public int getCount();
}
