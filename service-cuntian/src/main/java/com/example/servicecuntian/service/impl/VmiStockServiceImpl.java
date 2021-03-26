package com.example.servicecuntian.service.impl;

import com.example.servicecuntian.dao.VmiStockDao;
import com.example.servicecuntian.model.Vmistock;
import com.example.servicecuntian.service.ServiceExceptionUtil;
import com.example.servicecuntian.service.VmiStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VmiStockServiceImpl implements VmiStockService {
    @Autowired
    private VmiStockDao vmiStockDao;

    @Autowired
    private ServiceExceptionUtil serviceExceptionUtil;

    @Override
    public List<Vmistock> getVmiStocks(int startNum, int pageNum) {
        List<Vmistock> vmistocks = null;
        try {
            vmistocks = vmiStockDao.getVmiStocks(startNum, pageNum);
        } catch (Exception e) {
            serviceExceptionUtil.catchContent(e);
        }
        return vmistocks;
    }

    @Override
    public int getCount() {
        int count = 0;
        try {
            count = vmiStockDao.getCount();
        } catch (Exception e) {
            serviceExceptionUtil.catchContent(e);
        }
        return count;
    }

    @Override
    public String getLatestDateMark() {
        String date = null;
        try {
            date = vmiStockDao.getLatestDateMark();
        } catch (Exception e) {
            serviceExceptionUtil.catchContent(e);
        }
        return date;
    }
}
