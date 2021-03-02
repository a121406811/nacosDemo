package com.example.servicecuntian.service.impl;

import com.example.servicecuntian.dao.TradeVmiDao;
import com.example.servicecuntian.model.TradeVmi;
import com.example.servicecuntian.service.TradeVmiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeVmiServiceImpl implements TradeVmiService {
    @Autowired
    private TradeVmiDao tradeVmiDao;

    @Override
    public List<TradeVmi> getTradeVmis(String tradeDateFrom, String tradeDateTo, int startNum, int pageNum) {
        return tradeVmiDao.getTradeVmis(tradeDateFrom, tradeDateTo, startNum, pageNum);
    }

    @Override
    public int getCount() {
        return tradeVmiDao.getCount();
    }
}
