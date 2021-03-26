package com.example.servicecuntian.dao.impl;

import com.example.servicecuntian.dao.TradeVmiDao;

import com.example.servicecuntian.model.StockMovementHistory;
import com.example.servicecuntian.model.TradeVmi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TradeVmiImpl implements TradeVmiDao {

    @Autowired
    @Qualifier("kylinTemplate")
    private JdbcTemplate kylinTemplate;

    @Override
    public List<TradeVmi> getTradeVmis(String tradeDateFrom, String tradeDateTo, int startNum, int pageNum) throws Exception{
        String sql = "select seq,\n" +
                "       osa,\n" +
                "       disty_name,\n" +
                "       customer_code,\n" +
                "       end_customer_name,\n" +
                "       cpn,\n" +
                "       mpn,\n" +
                "       application,\n" +
                "       end_customer_part,\n" +
                "       trade_date,\n" +
                "       sum(trade_qty) as trade_qty\n" +
                "from hana_new_dbsyn.xh_trade_vmi_file\n" +
                "group by seq, osa, disty_name, customer_code, end_customer_name, cpn, mpn, application, end_customer_part, trade_date\n" +
                "having trade_date > ? and trade_date < ?\n" +
                "order by seq asc\n" +
                "limit ?," + pageNum;

        RowMapper<TradeVmi> rowMapper = new BeanPropertyRowMapper<>(TradeVmi.class);
        return kylinTemplate.query(sql, rowMapper, tradeDateFrom, tradeDateTo, startNum);
    }

    @Override
    public int getCount()throws Exception {
        String sql = "select count(seq) from hana_new_dbsyn.xh_trade_vmi_file";
        return kylinTemplate.queryForObject(sql, Integer.class);
    }

    /**
     * 查询kylin的最新的date_mark字段
     *
     * @return
     * @throws Exception
     */
    @Override
    public String getLatestDateMark() throws Exception {
        String sql = "select date_mark from hana_new_dbsyn.xh_trade_vmi_file order by date_mark desc limit 1,1;";
        return kylinTemplate.queryForObject(sql, String.class);
    }
}
