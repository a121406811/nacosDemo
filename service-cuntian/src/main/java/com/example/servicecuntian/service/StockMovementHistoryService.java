package com.example.servicecuntian.service;

import com.example.servicecuntian.model.StockMovementHistory;

import java.sql.SQLException;
import java.util.List;

public interface StockMovementHistoryService {

    List<StockMovementHistory> getStockMovementHistorys(String moveDateFrom, String moveDateTo, int startNum, int pageNum);

    int getCount();

    int getCountByDate(String from, String to);

    String getLatestDateMark();
}
