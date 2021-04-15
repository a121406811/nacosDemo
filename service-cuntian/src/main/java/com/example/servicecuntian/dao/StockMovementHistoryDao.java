package com.example.servicecuntian.dao;

import com.example.servicecuntian.model.StockMovementHistory;

import java.sql.SQLException;
import java.util.List;

public interface StockMovementHistoryDao {

    List<StockMovementHistory> getStockMovementHistorys(String moveDateFrom, String moveDateTo, int startNum, int pageNum) throws Exception;

    int getCount() throws Exception;

    int getCountByDate(String from, String to) throws Exception;

    String getLatestDateMark() throws Exception;

}
