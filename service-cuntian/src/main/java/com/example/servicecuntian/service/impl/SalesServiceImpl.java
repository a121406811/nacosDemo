package com.example.servicecuntian.service.impl;

import com.example.servicecuntian.dao.SalesDao;
import com.example.servicecuntian.model.Sales;
import com.example.servicecuntian.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesServiceImpl implements SalesService {
    @Autowired
    private SalesDao salesDao;

    @Override
    public List<Sales> getSales(String salesDateFrom, String salesDateTo, int startNum, int pageNum) {
        return salesDao.getSales(salesDateFrom, salesDateTo, startNum, pageNum);
    }

    @Override
    public int getCount() {
        return salesDao.getCount();
    }
}
