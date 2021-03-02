package com.example.servicecuntian.service;

import com.example.servicecuntian.model.StockMovementHistory;

import java.util.List;

public interface StockMovementHistoryService {

    public List<StockMovementHistory> getStockMovementHistorys(String moveDateFrom, String moveDateTo, int startNum, int pageNum);

    public int getCount();
}
