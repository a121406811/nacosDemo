package com.example.servicecuntian.dao.impl;

import com.example.servicecuntian.dao.ForecastDao;
import com.example.servicecuntian.model.Forecast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ForecastDaoImpl implements ForecastDao {

    @Autowired
    @Qualifier("kylinTemplate")
    private JdbcTemplate kylinTemplate;

    @Override
    public List<Forecast> getForecasts(Integer startNum, Integer pageNum) {
        String sql = "select seq,\n" +
                "       osa,\n" +
                "       disty_name,\n" +
                "       customer_code,\n" +
                "       end_costomer_name,\n" +
                "       cpn,\n" +
                "       mpn,\n" +
                "       application,\n" +
                "       end_customer_part,\n" +
                "       sum(n_forecast_qty)  as n_forecast_qty,\n" +
                "       sum(n1_forecast_qty) as n1_forecast_qty,\n" +
                "       sum(n2_forecast_qty) as n2_forecast_qty,\n" +
                "       sum(n3_forecast_qty) as n3_forecast_qty,\n" +
                "       sum(n4_forecast_qty) as n4_forecast_qty,\n" +
                "       flag,\n" +
                "       upload_date\n" +
                "from hana_new_dbsyn.xh_forecast_file\n" +
                "group by seq, osa, disty_name, customer_code, end_costomer_name, cpn, mpn, application, end_customer_part,\n" +
                "         flag, upload_date\n" +
                "order by seq asc \n" +
                "limit " + startNum + ", " + pageNum;

        RowMapper<Forecast> rowMapper = new BeanPropertyRowMapper<>(Forecast.class);
        return kylinTemplate.query(sql, rowMapper);
    }

    @Override
    public Integer getCount() {
        String sql = "select count(seq) from hana_new_dbsyn.xh_forecast_file";
        return kylinTemplate.queryForObject(sql, Integer.class);
    }
}
