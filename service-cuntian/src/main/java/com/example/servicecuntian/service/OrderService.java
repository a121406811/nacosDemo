package com.example.servicecuntian.service;

import com.example.servicecuntian.model.Order;

import java.util.List;

public interface OrderService {

    List<Order> getOrders(String orderDateFrom, String orderDateTo, int startNum, int pageNum);

    int getCount();

    int getCountByDate(String from, String to);

    String getLatestDateMark();
}
