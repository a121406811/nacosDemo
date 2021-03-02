package com.example.servicecuntian.service;

import com.example.servicecuntian.model.Purchase;

import java.util.List;

public interface PurchaseService {

    public List<Purchase> getPurchases(String purchaseDateFrom, String purchaseDateTo, int startNum, int pageNum);

    public int getCount();

}
