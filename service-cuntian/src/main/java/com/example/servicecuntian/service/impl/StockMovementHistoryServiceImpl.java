package com.example.servicecuntian.service.impl;

import com.example.servicecuntian.dao.StockMovementHistoryDao;
import com.example.servicecuntian.model.StockMovementHistory;
import com.example.servicecuntian.service.StockMovementHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockMovementHistoryServiceImpl implements StockMovementHistoryService {

    @Autowired
    private StockMovementHistoryDao stockMovementHistoryDao;

    @Override
    public List<StockMovementHistory> getStockMovementHistorys(String moveDateFrom, String moveDateTo, int startNum, int pageNum) {
        return stockMovementHistoryDao.getStockMovementHistorys(moveDateFrom, moveDateTo, startNum, pageNum);
    }

    @Override
    public int getCount() {
        return stockMovementHistoryDao.getCount();
    }

}
