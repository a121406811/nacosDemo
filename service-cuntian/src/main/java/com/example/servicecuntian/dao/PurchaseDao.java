package com.example.servicecuntian.dao;


import com.example.servicecuntian.model.Purchase;

import java.util.Date;
import java.util.List;

public interface PurchaseDao {

    List<Purchase> getPurchases(String purchaseDateFrom, String purchaseDateTo, int startNum, int pageNum) throws Exception;

    int getCount() throws Exception;

    String getLatestDateMark() throws Exception;

}
