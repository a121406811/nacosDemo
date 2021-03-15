package com.example.servicecuntian.dao.impl;

import com.example.servicecuntian.dao.PurchaseDao;
import com.example.servicecuntian.model.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class PurchaseDaoImpl implements PurchaseDao {

    @Autowired
    @Qualifier("kylinTemplate")
    private JdbcTemplate kylinTemplate;

    public List<Purchase> getPurchases(String purchaseDateFrom, String purchaseDateTo, int startNum, int pageNum) throws Exception {
        String sql = "select seq,\n" +
                "       osa,\n" +
                "       disty_name,\n" +
                "       customer_code,\n" +
                "       end_costomer_name,\n" +
                "       cpn,\n" +
                "       mpn,\n" +
                "       application,\n" +
                "       end_customer_part,\n" +
                "       warehouse,\n" +
                "       purchase_date,\n" +
                "       sum(purchase_qty) purchase_qty,\n" +
                "       murata_invoice_no\n" +
                "from hana_new_dbsyn.xh_purchase_file\n" +
                "group by seq, osa, disty_name, customer_code, end_costomer_name, cpn, mpn, application, end_customer_part,\n" +
                "         warehouse, purchase_date, murata_invoice_no\n" +
                "having purchase_date > ? and purchase_date < ? order by seq asc\n" +
                "limit ?," + pageNum;

        RowMapper<Purchase> rowMapper = new BeanPropertyRowMapper<>(Purchase.class);
        return kylinTemplate.query(sql, rowMapper, purchaseDateFrom, purchaseDateTo, startNum);
    }

    public int getCount() throws Exception {
        String sql = "select count(seq) from hana_new_dbsyn.xh_purchase_file";
        return kylinTemplate.queryForObject(sql, Integer.class);
    }

    /**
     * 查询kylin的date_mark字段
     * @return
     * @throws Exception
     */
    @Override
    public String getDateMark() throws Exception{
        String sql = "select date_mark from hana_new_dbsyn.xh_purchase_file limit 1,1;";
        return kylinTemplate.queryForObject(sql, String.class);
    }
}
