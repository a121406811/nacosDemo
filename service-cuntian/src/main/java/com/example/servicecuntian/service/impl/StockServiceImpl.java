package com.example.servicecuntian.service.impl;

import com.example.servicecuntian.dao.StockDao;
import com.example.servicecuntian.model.Stock;
import com.example.servicecuntian.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockDao stockDao;

    @Override
    public List<Stock> getStocks(int startNum, int pageNum) {
        return stockDao.getStocks(startNum, pageNum);
    }

    @Override
    public int getCount() {
        return stockDao.getCount();
    }
}
