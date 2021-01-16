package com.example.servicecuntian.controller;

import com.example.servicecuntian.model.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "村田接口")
@RequestMapping("/cuntianKylin")
@RestController
@RefreshScope
public class TestController {

    @Autowired
    @Qualifier("kylinTemplate")
    private JdbcTemplate kylinTemplate;

    @ApiOperation(value = "stock")     //这个注解必须要
    @GetMapping(value = "/stock")
    public List<Stock> stock() {
//        long startTime = System.currentTimeMillis(); //获取开始时间
        String sql = "select osa,\n" +
                "       distributor_name,\n" +
                "       customer_code,\n" +
                "       end_costomer_name,\n" +
                "       part_number,\n" +
                "       mpn,\n" +
                "       application,\n" +
                "       end_customer_part,\n" +
                "       warehouse_name,\n" +
                "       sum(stock_qty) stock_qty,\n" +
                "       dateformat,\n" +
                "       inhivetime\n" +
                "from hana_new_dbsyn.xh_stock_file\n" +
                "group by osa, distributor_name, customer_code, end_costomer_name, part_number, mpn, application, end_customer_part,\n" +
                "         warehouse_name, dateformat, inhivetime;\n";
        RowMapper<Stock> rowMapper = new BeanPropertyRowMapper<>(Stock.class);
        return kylinTemplate.query(sql, rowMapper);
    }

    @ApiOperation(value = "purchase")     //这个注解必须要
    @GetMapping(value = "/purchase")
    public List<Purchase> purchase() {
//        long startTime = System.currentTimeMillis(); //获取开始时间
        String sql = "select osa,\n" +
                "       distributor_name,\n" +
                "       customer_code,\n" +
                "       end_costomer_name,\n" +
                "       part_number,\n" +
                "       mpn,\n" +
                "       application,\n" +
                "       end_customer_part,\n" +
                "       warehouse_name,\n" +
                "       purchase_date,\n" +
                "       sum(purchase_qty) as purchase_qty,\n" +
                "       murata_invoice,\n" +
                "       dateformat,\n" +
                "       inhivetime\n" +
                "from hana_new_dbsyn.xh_purchase_file\n" +
                "group by osa, distributor_name, customer_code, end_costomer_name, part_number, mpn, application, end_customer_part,\n" +
                "         warehouse_name, purchase_date, murata_invoice, dateformat, inhivetime;";
        RowMapper<Purchase> rowMapper = new BeanPropertyRowMapper<>(Purchase.class);
        return kylinTemplate.query(sql, rowMapper);
    }

    @ApiOperation(value = "sales")     //这个注解必须要
    @GetMapping(value = "/sales")
    public List<Sales> sales() {
//        long startTime = System.currentTimeMillis(); //获取开始时间
        String sql = "select osa,\n" +
                "       distributor_name,\n" +
                "       customer_code,\n" +
                "       end_costomer_name,\n" +
                "       part_number,\n" +
                "       mpn,\n" +
                "       application,\n" +
                "       end_customer_part,\n" +
                "       sales_date,\n" +
                "       sum(sales_qty) as sales_qty,\n" +
                "       dateformat,\n" +
                "       inhivetime\n" +
                "from hana_new_dbsyn.xh_sales_file\n" +
                "group by osa, distributor_name, customer_code, end_costomer_name, part_number, mpn, application, \n" +
                "         end_customer_part, sales_date, dateformat, inhivetime;";
        RowMapper<Sales> rowMapper = new BeanPropertyRowMapper<>(Sales.class);
        return kylinTemplate.query(sql, rowMapper);
    }

    @ApiOperation(value = "forecast")     //这个注解必须要
    @GetMapping(value = "/forecast")
    public List<Forecast> forecast() {
//        long startTime = System.currentTimeMillis(); //获取开始时间
        String sql = "select osa,\n" +
                "       distributor_name,\n" +
                "       customer_code,\n" +
                "       end_costomer_name,\n" +
                "       part_number,\n" +
                "       mpn,\n" +
                "       application,\n" +
                "       end_customer_part,\n" +
                "       sum(n_month) as n_month,\n" +
                "       sum(n1_month) as n1_month,\n" +
                "       sum(n2_month) as n2_month,\n" +
                "       sum(n3_month) as n3_month,\n" +
                "       sum(n4_month) as n4_month,\n" +
                "       flag,\n" +
                "       upload_date,\n" +
                "       dateformat,\n" +
                "       inhivetime\n" +
                "from hana_new_dbsyn.xh_forecast_file group by osa, distributor_name, customer_code, end_costomer_name,\n" +
                "       part_number, mpn, application, end_customer_part, flag, upload_date, dateformat, inhivetime";
        RowMapper<Forecast> rowMapper = new BeanPropertyRowMapper<>(Forecast.class);
        return kylinTemplate.query(sql, rowMapper);
    }

    @ApiOperation(value = "tradeVmi")     //这个注解必须要
    @GetMapping(value = "/tradeVmi")
    public List<TradeVmi> tradeVmi() {
//        long startTime = System.currentTimeMillis(); //获取开始时间
        String sql = "select osa,\n" +
                "       distributor_name,\n" +
                "       customer_code,\n" +
                "       end_costomer_name,\n" +
                "       part_number,\n" +
                "       mpn,\n" +
                "       application,\n" +
                "       end_customer_part,\n" +
                "       trade_date,\n" +
                "       sum(trade_qty) as trade_qty,\n" +
                "       dateformat,\n" +
                "       vmi_warehouse_name,\n" +
                "       inhivetime\n" +
                "from hana_new_dbsyn.xh_trade_vmi_file\n" +
                "group by osa, distributor_name, customer_code, end_costomer_name, part_number, mpn, application,\n" +
                "         end_customer_part, trade_date, dateformat, vmi_warehouse_name, inhivetime";
        RowMapper<TradeVmi> rowMapper = new BeanPropertyRowMapper<>(TradeVmi.class);
        return kylinTemplate.query(sql, rowMapper);
    }

    @ApiOperation(value = "order")     //这个注解必须要
    @GetMapping(value = "/order")
    public List<Order> order() {
//        long startTime = System.currentTimeMillis(); //获取开始时间
        String sql = "select osa,\n" +
                "       distributor_name,\n" +
                "       customer_code,\n" +
                "       end_costomer_name,\n" +
                "       part_number,\n" +
                "       mpn,\n" +
                "       application,\n" +
                "       end_customer_part,\n" +
                "       order_date,\n" +
                "       sum(order_qty) as order_qty,\n" +
                "       dateformat,\n" +
                "       inhivetime\n" +
                "from hana_new_dbsyn.xh_order_file\n" +
                "group by osa, distributor_name, customer_code, end_costomer_name, part_number, mpn, application,\n" +
                "         end_customer_part, order_date, dateformat, inhivetime";
        RowMapper<Order> rowMapper = new BeanPropertyRowMapper<>(Order.class);
        return kylinTemplate.query(sql, rowMapper);
    }

    @ApiOperation(value = "stockMovementHistory")     //这个注解必须要
    @GetMapping(value = "/stockMovementHistory")
    public List<StockMovementHistory> stockMovementHistory() {
//        long startTime = System.currentTimeMillis(); //获取开始时间
        String sql = "select osa,\n" +
                "       distributor_name,\n" +
                "       customer_code,\n" +
                "       end_costomer_name,\n" +
                "       part_number,\n" +
                "       mpn,\n" +
                "       application,\n" +
                "       end_customer_part,\n" +
                "       warehouse_name,\n" +
                "       stock_movement_date,\n" +
                "       sum(stock_movement_qty) as stock_movement_qty,\n" +
                "       flag,\n" +
                "       dateformat,\n" +
                "       inhivetime\n" +
                "from hana_new_dbsyn.xh_stock_movement_history\n" +
                "group by osa, distributor_name, customer_code, end_costomer_name, part_number, mpn, application,\n" +
                "         end_customer_part, warehouse_name, stock_movement_date, flag, dateformat,\n" +
                "         inhivetime";
        RowMapper<StockMovementHistory> rowMapper = new BeanPropertyRowMapper<>(StockMovementHistory.class);
        return kylinTemplate.query(sql, rowMapper);
    }

    @ApiOperation(value = "vmistock")     //这个注解必须要
    @GetMapping(value = "/vmistock")
    public List<Vmistock> vmistock() {
//        long startTime = System.currentTimeMillis(); //获取开始时间
        String sql = "select osa,\n" +
                "       distributor_name,\n" +
                "       customer_code,\n" +
                "       end_costomer_name,\n" +
                "       part_number,\n" +
                "       mpn,\n" +
                "       application,\n" +
                "       end_customer_part,\n" +
                "       warehouse_name,\n" +
                "       sum(vmistock_qty) as vmistock_qty,\n" +
                "       dateformat,\n" +
                "       inhivetime\n" +
                "from hana_new_dbsyn.xh_vmistock_file\n" +
                "group by osa, distributor_name, customer_code, end_costomer_name,\n" +
                "         part_number, mpn, application, end_customer_part, warehouse_name, dateformat, inhivetime";
        RowMapper<Vmistock> rowMapper = new BeanPropertyRowMapper<>(Vmistock.class);
        return kylinTemplate.query(sql, rowMapper);
    }
}
