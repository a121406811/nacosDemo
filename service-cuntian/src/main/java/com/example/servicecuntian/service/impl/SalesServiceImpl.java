package com.example.servicecuntian.service.impl;

import com.example.servicecuntian.dao.SalesDao;
import com.example.servicecuntian.model.Sales;
import com.example.servicecuntian.service.SalesService;
import com.example.servicecuntian.service.ServiceExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesServiceImpl implements SalesService {
    @Autowired
    private SalesDao salesDao;

    @Autowired
    private ServiceExceptionUtil serviceExceptionUtil;

    @Override
    public List<Sales> getSales(String salesDateFrom, String salesDateTo, int startNum, int pageNum) {
        List<Sales> sales = null;
        try {
            sales = salesDao.getSales(salesDateFrom, salesDateTo, startNum, pageNum);
        } catch (Exception e) {
            serviceExceptionUtil.catchContent(e);
        }
        return sales;
    }

    @Override
    public int getCount() {
        int count = 0;
        try {
            count = salesDao.getCount();
        } catch (Exception e) {
            serviceExceptionUtil.catchContent(e);
        }
        return count;
    }

    @Override
    public String getLatestDateMark() {
        String date = null;
        try {
            date = salesDao.getLatestDateMark();
        } catch (Exception e) {
            serviceExceptionUtil.catchContent(e);
        }
        return date;
    }
}
