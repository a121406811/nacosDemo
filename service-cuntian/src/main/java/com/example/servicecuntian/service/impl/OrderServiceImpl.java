package com.example.servicecuntian.service.impl;

import com.example.servicecuntian.dao.OrderDao;
import com.example.servicecuntian.model.Order;
import com.example.servicecuntian.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Override
    public List<Order> getOrders(String orderDateFrom, String orderDateTo, int startNum, int pageNum) {
        return orderDao.getOrders(orderDateFrom, orderDateTo, startNum, pageNum);
    }

    @Override
    public int getCount() {
        return orderDao.getCount();
    }
}
