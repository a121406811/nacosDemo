package com.example.servicecuntian.dao.impl;

import com.example.servicecuntian.dao.VmiStockDao;
import com.example.servicecuntian.model.Vmistock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VmiStockDaoimpl implements VmiStockDao {

    @Autowired
    @Qualifier("kylinTemplate")
    private JdbcTemplate kylinTemplate;

    @Value("${databaseName}")
    private String databaseName;

    @Override
    public List<Vmistock> getVmiStocks(int startNum, int pageNum) throws Exception {
        String sql = "select seq,\n" +
                "       osa,\n" +
                "       disty_name,\n" +
                "       customer_code,\n" +
                "       end_customer_name,\n" +
                "       cpn,\n" +
                "       mpn,\n" +
                "       application,\n" +
                "       end_customer_part,\n" +
                "       warhouse,\n" +
                "       sum(vmi_stock_qty) as vmi_stock_qty\n" +
                "from " + databaseName + ".xh_vmistock_file\n" +
                "group by seq, osa, disty_name, customer_code, end_customer_name, cpn, mpn, application, end_customer_part, warhouse\n" +
                "order by seq asc\n" +
                "limit " + startNum + "," + pageNum;
        RowMapper<Vmistock> rowMapper = new BeanPropertyRowMapper<>(Vmistock.class);
        return kylinTemplate.query(sql, rowMapper);
    }

    @Override
    public int getCount() throws Exception {
        String sql = "select count(seq) from " + databaseName + ".xh_vmistock_file";
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
        String sql = "select date_mark from " + databaseName + ".xh_vmistock_file order by date_mark desc limit 1,1;";
        return kylinTemplate.queryForObject(sql, String.class);
    }
}
