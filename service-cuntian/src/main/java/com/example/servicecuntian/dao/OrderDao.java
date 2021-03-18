package com.example.servicecuntian.dao;

import com.example.servicecuntian.model.Order;

import java.util.List;

public interface OrderDao {

    public List<Order> getOrders(String orderDateFrom, String orderDateTo, int startNum, int pageNum) throws Exception;

    public int getCount() throws Exception;

    public String getLatestDateMark() throws Exception;

}
