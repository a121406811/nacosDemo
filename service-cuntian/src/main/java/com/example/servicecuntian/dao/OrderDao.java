package com.example.servicecuntian.dao;

import com.example.servicecuntian.model.Order;

import java.util.List;

public interface OrderDao {

    List<Order> getOrders(String orderDateFrom, String orderDateTo, int startNum, int pageNum) throws Exception;

    int getCount() throws Exception;

    public int getCountByDate(String from, String to) throws Exception;

    String getLatestDateMark() throws Exception;

}
