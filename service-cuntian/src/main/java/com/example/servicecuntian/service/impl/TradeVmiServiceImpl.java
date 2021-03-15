package com.example.servicecuntian.service.impl;

import com.example.servicecuntian.dao.TradeVmiDao;
import com.example.servicecuntian.model.TradeVmi;
import com.example.servicecuntian.service.ServiceExceptionUtil;
import com.example.servicecuntian.service.TradeVmiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeVmiServiceImpl implements TradeVmiService {
    @Autowired
    private TradeVmiDao tradeVmiDao;

    @Autowired
    private ServiceExceptionUtil serviceExceptionUtil;

    @Override
    public List<TradeVmi> getTradeVmis(String tradeDateFrom, String tradeDateTo, int startNum, int pageNum) {
        List<TradeVmi> tradeVmis = null;
        try {
            tradeVmis = tradeVmiDao.getTradeVmis(tradeDateFrom, tradeDateTo, startNum, pageNum);
        } catch (Exception e) {
            serviceExceptionUtil.catchContent(e);
        }
        return tradeVmis;
    }

    @Override
    public int getCount() {
        int count = 0;
        try {
            count = tradeVmiDao.getCount();
        } catch (Exception e) {
            serviceExceptionUtil.catchContent(e);
        }
        return count;
    }
}
