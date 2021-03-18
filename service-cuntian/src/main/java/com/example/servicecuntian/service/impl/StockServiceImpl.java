package com.example.servicecuntian.service.impl;

import com.example.servicecuntian.dao.StockDao;
import com.example.servicecuntian.exception.BusinessException;
import com.example.servicecuntian.model.Stock;
import com.example.servicecuntian.service.ServiceExceptionUtil;
import com.example.servicecuntian.service.StockService;
import com.example.servicecuntian.util.EmailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockDao stockDao;

    @Autowired
    private ServiceExceptionUtil serviceExceptionUtil;

    @Override
    public List<Stock> getStocks(int startNum, int pageNum) {
        List<Stock> stocks = null;
        try {
            stocks = stockDao.getStocks(startNum, pageNum);
        } catch (Exception e) {
            serviceExceptionUtil.catchContent(e);
        }
        return stocks;
    }

    @Override
    public int getCount() {
        int count = 0;
        try {
            count = stockDao.getCount();
        } catch (Exception e) {
            serviceExceptionUtil.catchContent(e);
        }
        return count;
    }

    @Override
    public String getLatestDateMark() {
        String date = null;
        try {
            date = stockDao.getLatestDateMark();
        } catch (Exception e) {
            serviceExceptionUtil.catchContent(e);
        }
        return date;
    }
}
