package com.example.servicecuntian.dao;

import com.example.servicecuntian.model.StockMovementHistory;
import com.example.servicecuntian.model.TradeVmi;

import java.util.List;

public interface TradeVmiDao {

    List<TradeVmi> getTradeVmis(String tradeDateFrom, String tradeDateTo, int startNum, int pageNum) throws Exception;

    int getCount() throws Exception;

    String getLatestDateMark() throws Exception;

}
