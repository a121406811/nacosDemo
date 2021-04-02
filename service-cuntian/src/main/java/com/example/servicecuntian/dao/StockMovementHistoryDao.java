package com.example.servicecuntian.dao;

import com.example.servicecuntian.model.StockMovementHistory;

import java.sql.SQLException;
import java.util.List;

public interface StockMovementHistoryDao {

    List<StockMovementHistory> getStockMovementHistorys(String moveDateFrom, String moveDateTo, int startNum, int pageNum) throws Exception;

    int getCount() throws Exception;

    String getLatestDateMark() throws Exception;

}
