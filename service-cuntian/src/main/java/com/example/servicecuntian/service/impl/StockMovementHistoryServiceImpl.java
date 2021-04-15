package com.example.servicecuntian.service.impl;

import com.example.servicecuntian.dao.StockMovementHistoryDao;
import com.example.servicecuntian.model.StockMovementHistory;
import com.example.servicecuntian.service.ServiceExceptionUtil;
import com.example.servicecuntian.service.StockMovementHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class StockMovementHistoryServiceImpl implements StockMovementHistoryService {

    @Autowired
    private StockMovementHistoryDao stockMovementHistoryDao;

    @Autowired
    private ServiceExceptionUtil serviceExceptionUtil;

    @Override
    public List<StockMovementHistory> getStockMovementHistorys(String moveDateFrom, String moveDateTo, int startNum, int pageNum) {
        List<StockMovementHistory> stockMovementHistorys = null;
        try {
            stockMovementHistorys = stockMovementHistoryDao.getStockMovementHistorys(moveDateFrom, moveDateTo, startNum, pageNum);
        } catch (Exception e) {
            serviceExceptionUtil.catchContent(e);
        }
        return stockMovementHistorys;
    }

    @Override
    public int getCount() {
        int count = 0;
        try {
            count = stockMovementHistoryDao.getCount();
        } catch (Exception e) {
            serviceExceptionUtil.catchContent(e);
        }
        return count;
    }

    @Override
    public int getCountByDate(String from, String to) {
        int count = 0;
        try {
            count = stockMovementHistoryDao.getCountByDate(from, to);
        } catch (Exception e) {
            serviceExceptionUtil.catchContent(e);
        }
        return count;
    }

    @Override
    public String getLatestDateMark() {
        String date = null;
        try {
            date = stockMovementHistoryDao.getLatestDateMark();
        } catch (Exception e) {
            serviceExceptionUtil.catchContent(e);
        }
        return date;
    }

}
