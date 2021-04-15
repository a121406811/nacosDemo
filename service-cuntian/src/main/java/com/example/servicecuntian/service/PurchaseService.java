package com.example.servicecuntian.service;

import com.example.servicecuntian.model.Purchase;

import java.util.Date;
import java.util.List;

public interface PurchaseService {

    List<Purchase> getPurchases(String purchaseDateFrom, String purchaseDateTo, int startNum, int pageNum);

    int getCount();

    int getCountByDate(String from, String to);

    String getLatestDateMark();

}
