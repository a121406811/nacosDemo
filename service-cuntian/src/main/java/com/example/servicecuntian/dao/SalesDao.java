package com.example.servicecuntian.dao;

import com.example.servicecuntian.model.Sales;

import java.util.List;

public interface SalesDao {

    public List<Sales> getSales(String salesDateFrom, String salesDateTo, int startNum, int pageNum) throws Exception;

    public int getCount() throws Exception;
}
