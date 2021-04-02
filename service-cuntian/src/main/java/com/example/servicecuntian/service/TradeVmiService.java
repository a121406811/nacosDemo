package com.example.servicecuntian.service;

import com.example.servicecuntian.model.TradeVmi;

import java.util.List;

public interface TradeVmiService {

    List<TradeVmi> getTradeVmis(String tradeDateFrom, String tradeDateTo, int startNum, int pageNum);

    int getCount();

    String getLatestDateMark();
}
