package com.example.servicecuntian.dao;

import com.example.servicecuntian.model.StockMovementHistory;

import java.sql.SQLException;
import java.util.List;

public interface StockMovementHistoryDao {

    public List<StockMovementHistory> getStockMovementHistorys(String moveDateFrom, String moveDateTo, int startNum, int pageNum) throws Exception;

    public int getCount() throws Exception;

    public String getLatestDateMark() throws Exception;

}
