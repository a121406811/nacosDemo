package com.example.servicecuntian.dao.impl;

import com.example.servicecuntian.dao.VmistockDao;
import com.example.servicecuntian.model.StockMovementHistory;
import com.example.servicecuntian.model.Vmistock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VmistockDaoimpl implements VmistockDao {

    @Autowired
    @Qualifier("kylinTemplate")
    private JdbcTemplate kylinTemplate;

    @Override
    public List<Vmistock> getVmistocks(int startNum, int pageNum) {
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
                "       sum(vmi_stock_qty) as vmi_stock_qty\n" +
                "from hana_new_dbsyn.xh_vmistock_file\n" +
                "group by seq, osa, disty_name, customer_code, end_costomer_name, cpn, mpn, application, end_customer_part, warhouse\n" +
                "order by seq asc\n" +
                "limit " + startNum + "," + pageNum;
        RowMapper<Vmistock> rowMapper = new BeanPropertyRowMapper<>(Vmistock.class);
        return kylinTemplate.query(sql, rowMapper);
    }

    @Override
    public int getCount() {
        String sql = "select count(seq) from hana_new_dbsyn.xh_vmistock_file";
        return kylinTemplate.queryForObject(sql, Integer.class);
    }
}
