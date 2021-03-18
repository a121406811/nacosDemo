package com.example.servicecuntian.dao.impl;

import com.example.servicecuntian.dao.StockMovementHistoryDao;
import com.example.servicecuntian.model.StockMovementHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StockMovementHistoryDaoImpl implements StockMovementHistoryDao {

    @Autowired
    @Qualifier("kylinTemplate")
    private JdbcTemplate kylinTemplate;

    public List<StockMovementHistory> getStockMovementHistorys(String moveDateFrom, String moveDateTo, int startNum, int pageNum) throws Exception {
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
                "       move_date,\n" +
                "       sum(move_qty) as move_qty,\n" +
                "       flag\n" +
                "from hana_new_dbsyn.xh_stock_movement_history group by\n" +
                "seq,osa,disty_name,customer_code,end_costomer_name,cpn,mpn,\n" +
                "       application,end_customer_part,warehouse,move_date,flag\n" +
                " having move_date> ? and move_date< ? order by seq asc" +
                "       limit ?," + pageNum;
        RowMapper<StockMovementHistory> rowMapper = null;
        rowMapper = new BeanPropertyRowMapper<>(StockMovementHistory.class);
        return kylinTemplate.query(sql, rowMapper, moveDateFrom, moveDateTo, startNum);
    }

    public int getCount() throws Exception {
        String sql = "select count(seq) from hana_new_dbsyn.xh_stock_movement_history";
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
        String sql = "select date_mark from hana_new_dbsyn.xh_stock_movement_history order by date_mark desc limit 1,1;";
        return kylinTemplate.queryForObject(sql, String.class);
    }
}
