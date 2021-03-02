package com.example.servicecuntian.service.impl;

import com.example.servicecuntian.dao.PurchaseDao;
import com.example.servicecuntian.model.Purchase;
import com.example.servicecuntian.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private PurchaseDao purchaseDao;

    @Override
    public List<Purchase> getPurchases(String purchaseDateFrom, String purchaseDateTo, int startNum, int pageNum) {
        return purchaseDao.getPurchases(purchaseDateFrom, purchaseDateTo, startNum, pageNum);
    }

    @Override
    public int getCount() {
        return purchaseDao.getCount();
    }
}
