package com.example.servicecuntian.dao;


import com.example.servicecuntian.model.Purchase;

import java.util.List;

public interface PurchaseDao {

    public List<Purchase> getPurchases(String purchaseDateFrom, String purchaseDateTo, int startNum, int pageNum);

    public int getCount();

}
