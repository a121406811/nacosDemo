package com.example.servicecuntian.dao.impl;

import com.example.servicecuntian.dao.OrderDao;

import com.example.servicecuntian.model.Forecast;
import com.example.servicecuntian.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {

    @Autowired
    @Qualifier("kylinTemplate")
    private JdbcTemplate kylinTemplate;

    @Override
    public List<Order> getOrders(String orderDateFrom, String orderDateTo, int startNum, int pageNum) {
        String sql = "select seq,\n" +
                "       osa,\n" +
                "       disty_name,\n" +
                "       customer_code,\n" +
                "       end_costomer_name,\n" +
                "       cpn,\n" +
                "       mpn,\n" +
                "       application,\n" +
                "       end_customer_part,\n" +
                "       order_date,\n" +
                "       sum(order_qty) as order_qty\n" +
                "from hana_new_dbsyn.xh_order_file\n" +
                "group by seq, osa, disty_name, customer_code, end_costomer_name, cpn, mpn, application, end_customer_part, order_date\n" +
                "having order_date > ? and order_date < ?\n" +
                "order by seq asc \n" +
                "limit ?," + pageNum;

        RowMapper<Order> rowMapper = new BeanPropertyRowMapper<>(Order.class);
        return kylinTemplate.query(sql, rowMapper, orderDateFrom, orderDateTo, startNum);
    }

    @Override
    public int getCount() {
        String sql = "select count(seq) from hana_new_dbsyn.xh_order_file";
        return kylinTemplate.queryForObject(sql, Integer.class);
    }
}
