package com.example.servicecuntian.dao.impl;

import com.example.servicecuntian.dao.StockDao;
import com.example.servicecuntian.model.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public class StockDaoImpl implements StockDao {

    @Autowired
    @Qualifier("kylinTemplate")
    private JdbcTemplate kylinTemplate;

    public List<Stock> getStocks(int startNum, int pageNum) throws Exception {
        String sql = "select seq,\n" +
                "       osa,\n" +
                "       disty_name,\n" +
                "       customer_code,\n" +
                "       end_costomer_name,\n" +
                "       cpn,\n" +
                "       mpn,\n" +
                "       application,\n" +
                "       end_customer_part,\n" +
                "       warhouse,\n" +
                "       sum(stock_qty) as stock_qty\n" +
                "from hana_new_dbsyn.xh_stock_file\n" +
                "group by seq, osa, disty_name, customer_code, end_costomer_name, cpn, mpn, application, end_customer_part, warhouse\n" +
                " order by seq asc limit " + startNum + "," + pageNum;
        RowMapper<Stock> rowMapper = null;
        rowMapper = new BeanPropertyRowMapper<>(Stock.class);
        return kylinTemplate.query(sql, rowMapper);
    }

    public int getCount() throws Exception {
        String sql = "select count(seq) from hana_new_dbsyn.xh_stock_file";
        Integer count = 0;
        count = kylinTemplate.queryForObject(sql, Integer.class);
        return count;
    }

    /**
     * 查询kylin的最新的date_mark字段
     *
     * @return
     * @throws Exception
     */
    @Override
    public String getLatestDateMark() throws Exception {
        String sql = "select date_mark from hana_new_dbsyn.xh_stock_file order by date_mark desc limit 1,1;";
        return kylinTemplate.queryForObject(sql, String.class);
    }
}
