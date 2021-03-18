package com.example.servicecuntian.service;

import com.example.servicecuntian.model.TradeVmi;

import java.util.List;

public interface TradeVmiService {

    public List<TradeVmi> getTradeVmis(String tradeDateFrom, String tradeDateTo, int startNum, int pageNum);

    public int getCount();

    public String getLatestDateMark();
}
