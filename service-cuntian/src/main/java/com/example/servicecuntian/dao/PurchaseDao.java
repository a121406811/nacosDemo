package com.example.servicecuntian.dao;


import com.example.servicecuntian.model.Purchase;

import java.util.Date;
import java.util.List;

public interface PurchaseDao {

    public List<Purchase> getPurchases(String purchaseDateFrom, String purchaseDateTo, int startNum, int pageNum) throws Exception;

    public int getCount() throws Exception;

    public String getLatestDateMark() throws Exception;

}
