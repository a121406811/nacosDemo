package com.example.servicecuntian.service;

import com.example.servicecuntian.model.Order;

import java.util.List;

public interface OrderService {

    public List<Order> getOrders(String orderDateFrom, String orderDateTo, int startNum, int pageNum);

    public int getCount();
}
