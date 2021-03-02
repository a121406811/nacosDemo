package com.example.servicecuntian.dao;

import com.example.servicecuntian.model.StockMovementHistory;

import java.util.List;

public interface StockMovementHistoryDao {

    public List<StockMovementHistory> getStockMovementHistorys(String moveDateFrom, String moveDateTo, int startNum, int pageNum);

    public int getCount();

}
