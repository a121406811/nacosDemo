package com.example.servicecuntian.dao.impl;

import com.example.servicecuntian.dao.SalesDao;
import com.example.servicecuntian.model.Sales;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SalesDaoImpl implements SalesDao {

    @Autowired
    @Qualifier("kylinTemplate")
    private JdbcTemplate kylinTemplate;

    @Override
    public List<Sales> getSales(String salesDateFrom, String salesDateTo, int startNum, int pageNum) {
        String sql = "select seq,\n" +
                "       osa,\n" +
                "       disty_name,\n" +
                "       customer_code,\n" +
                "       end_costomer_name,\n" +
                "       cpn,\n" +
                "       mpn,\n" +
                "       application,\n" +
                "       end_customer_part,\n" +
                "       sales_date,\n" +
                "       sum(sales_qty) as sales_qty\n" +
                "from hana_new_dbsyn.xh_sales_file\n" +
                "group by seq, osa, disty_name, customer_code, end_costomer_name, cpn, mpn, application, end_customer_part, sales_date\n" +
                "having sales_date > ? and sales_date < ? order by seq asc\n" +
                "limit ?," + pageNum;

        RowMapper<Sales> rowMapper = new BeanPropertyRowMapper<>(Sales.class);
        return kylinTemplate.query(sql, rowMapper, salesDateFrom, salesDateTo, startNum);
    }

    @Override
    public int getCount() {
        String sql = "select count(seq) from hana_new_dbsyn.xh_sales_file";
        return kylinTemplate.queryForObject(sql, Integer.class);
    }
}
