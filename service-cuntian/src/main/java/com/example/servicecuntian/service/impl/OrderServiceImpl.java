package com.example.servicecuntian.service.impl;

import com.example.servicecuntian.dao.OrderDao;
import com.example.servicecuntian.model.Order;
import com.example.servicecuntian.service.OrderService;
import com.example.servicecuntian.service.ServiceExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ServiceExceptionUtil serviceExceptionUtil;

    @Override
    public List<Order> getOrders(String orderDateFrom, String orderDateTo, int startNum, int pageNum) {
        List<Order> orders = null;
        try {
            orders = orderDao.getOrders(orderDateFrom, orderDateTo, startNum, pageNum);
        } catch (Exception e) {
            serviceExceptionUtil.catchContent(e);
        }
        return orders;
    }

    @Override
    public int getCount() {
        int count = 0;
        try {
            count = orderDao.getCount();
        } catch (Exception e) {
            serviceExceptionUtil.catchContent(e);
        }
        return count;
    }

    @Override
    public int getCountByDate(String from, String to) {
        int count = 0;
        try {
            count = orderDao.getCountByDate(from, to);
        } catch (Exception e) {
            serviceExceptionUtil.catchContent(e);
        }
        return count;
    }

    @Override
    public String getLatestDateMark() {
        String latestDateMark = null;
        try {
            latestDateMark = orderDao.getLatestDateMark();
        } catch (Exception e) {
            serviceExceptionUtil.catchContent(e);
        }
        return latestDateMark;
    }
}
