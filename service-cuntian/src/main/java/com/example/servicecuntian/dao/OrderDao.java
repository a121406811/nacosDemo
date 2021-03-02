package com.example.servicecuntian.dao;

import com.example.servicecuntian.model.Order;

import java.util.List;

public interface OrderDao {

    public List<Order> getOrders(String orderDateFrom, String orderDateTo, int startNum, int pageNum);

    public int getCount();
}
