package com.example.servicecuntian.dao;

import com.example.servicecuntian.model.Stock;

import java.sql.SQLException;
import java.util.List;

public interface StockDao {

    public List<Stock> getStocks(int startNum, int pageNum) throws Exception;

    public int getCount() throws Exception;
}
