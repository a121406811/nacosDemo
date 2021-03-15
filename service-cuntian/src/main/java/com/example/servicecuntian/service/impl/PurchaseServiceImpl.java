package com.example.servicecuntian.service.impl;

import com.example.servicecuntian.dao.PurchaseDao;
import com.example.servicecuntian.model.Purchase;
import com.example.servicecuntian.service.PurchaseService;
import com.example.servicecuntian.service.ServiceExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private PurchaseDao purchaseDao;

    @Autowired
    private ServiceExceptionUtil serviceExceptionUtil;

    @Override
    public List<Purchase> getPurchases(String purchaseDateFrom, String purchaseDateTo, int startNum, int pageNum) {
        List<Purchase> purchases = null;
        try {
            purchases = purchaseDao.getPurchases(purchaseDateFrom, purchaseDateTo, startNum, pageNum);
        } catch (Exception e) {
            serviceExceptionUtil.catchContent(e);
        }
        return purchases;
    }

    @Override
    public int getCount() {
        int count = 0;
        try {
            count = purchaseDao.getCount();
        } catch (Exception e) {
            serviceExceptionUtil.catchContent(e);
        }
        return count;
    }

    @Override
    public String getDateMark() {
        String date = null;
        try {
            date = purchaseDao.getDateMark();
        } catch (Exception e) {
            serviceExceptionUtil.catchContent(e);
        }
        return date;
    }
}
