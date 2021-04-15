package com.example.servicecuntian.dao;

import com.example.servicecuntian.model.Sales;

import java.util.List;

public interface SalesDao {

    List<Sales> getSales(String salesDateFrom, String salesDateTo, int startNum, int pageNum) throws Exception;

    int getCount() throws Exception;

    int getCountByDate(String from, String to) throws Exception;

    String getLatestDateMark() throws Exception;

}
