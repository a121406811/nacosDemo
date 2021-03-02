package com.example.servicecuntian.controller;

import com.example.servicecuntian.model.*;
import com.example.servicecuntian.service.*;
import com.example.servicecuntian.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@Slf4j
@Api(value = "村田接口")
@RequestMapping("/cuntianKylin")
@RestController
@RefreshScope
public class TestController {

    private static final int pageNum = 100;

    @Autowired
    private StockService stockService;

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private SalesService salesService;

    @Autowired
    private ForecastService forecastService;

    @Autowired
    private TradeVmiService tradeVmiService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private StockMovementHistoryService stockMovementHistoryService;

    @Autowired
    private VmistockService vmistockService;


    @ApiOperation(value = "stock")     //这个注解必须要
    @GetMapping(value = "/stock")
    public Map<String, Object> stock(int lastRecordNum) {
        int count = stockService.getCount();
        verificationLastRecordNum(lastRecordNum, count);
        Integer startNum = getStartNum(lastRecordNum);
        List<Stock> stocks = stockService.getStocks(startNum, pageNum);
        return Result.result(count, stocks.size(), stocks);
    }

    @ApiOperation(value = "purchase")     //这个注解必须要
    @GetMapping(value = "/purchase")
    public Map<String, Object> purchase(String purchaseDateFrom, String purchaseDateTo, int lastRecordNum) {
        int count = purchaseService.getCount();
        verificationLastRecordNum(lastRecordNum, count);
        Integer startNum = getStartNum(lastRecordNum);
        List<Purchase> purchases = purchaseService.getPurchases(purchaseDateFrom, purchaseDateTo, startNum, pageNum);
        return Result.result(count, purchases.size(), purchases);
    }

    @ApiOperation(value = "sales")     //这个注解必须要
    @GetMapping(value = "/sales")
    public Map<String, Object> sales(String salesDateFrom, String salesDateTo, int lastRecordNum) {
        int count = salesService.getCount();
        verificationLastRecordNum(lastRecordNum, count);
        Integer startNum = getStartNum(lastRecordNum);
        List<Sales> saless = salesService.getSales(salesDateFrom, salesDateTo, startNum, pageNum);
        return Result.result(count, saless.size(), saless);
    }

    @ApiOperation(value = "forecast")     //这个注解必须要
    @GetMapping(value = "/forecast")
    public Map<String, Object> forecast(int lastRecordNum) {
        int count = forecastService.getCount();
        verificationLastRecordNum(lastRecordNum, count);
        Integer startNum = getStartNum(lastRecordNum);
        List<Forecast> forecasts = forecastService.getForecasts(startNum, pageNum);
        return Result.result(count, forecasts.size(), forecasts);
    }

    @ApiOperation(value = "tradeVmi")     //这个注解必须要
    @GetMapping(value = "/tradeVmi")
    public Map<String, Object> tradeVmi(String tradeDateFrom, String tradeDateTo, int lastRecordNum) {
        int count = tradeVmiService.getCount();
        verificationLastRecordNum(lastRecordNum, count);
        Integer startNum = getStartNum(lastRecordNum);
        List<TradeVmi> tradeVmis = tradeVmiService.getTradeVmis(tradeDateFrom, tradeDateTo, startNum, pageNum);
        return Result.result(count, tradeVmis.size(), tradeVmis);
    }

    @ApiOperation(value = "order")     //这个注解必须要
    @GetMapping(value = "/order")
    public Map<String, Object> order(String orderDateFrom, String orderDateTo, int lastRecordNum) {
        int count = orderService.getCount();
        verificationLastRecordNum(lastRecordNum, count);
        Integer startNum = getStartNum(lastRecordNum);
        List<Order> orders = orderService.getOrders(orderDateFrom, orderDateTo, startNum, pageNum);
        return Result.result(count, orders.size(), orders);
    }


    @ApiOperation(value = "stockMovementHistory")     //这个注解必须要
    @GetMapping(value = "/stockMovementHistory")
    public Map<String, Object> stockMovementHistory(String moveDateFrom,
                                                    String moveDateTo,
                                                    int lastRecordNum) {
        int count = stockMovementHistoryService.getCount();
        verificationLastRecordNum(lastRecordNum, count);
        Integer startNum = getStartNum(lastRecordNum);
        List<StockMovementHistory> stockMovementHistorys = stockMovementHistoryService.getStockMovementHistorys(moveDateFrom, moveDateTo, startNum, pageNum);
        return Result.result(count, stockMovementHistorys.size(), stockMovementHistorys);
    }

    @ApiOperation(value = "vmistock")     //这个注解必须要
    @GetMapping(value = "/vmistock")
    public Map<String, Object> vmistock(int lastRecordNum) {
        int count = vmistockService.getCount();
        verificationLastRecordNum(lastRecordNum, count);
        Integer startNum = getStartNum(lastRecordNum);
        List<Vmistock> vmistocks = vmistockService.getVmistocks(startNum, pageNum);
        return Result.result(count, vmistocks.size(), vmistocks);
    }

    private int getStartNum(int lastRecordNum) {
        return lastRecordNum;
    }

    private void verificationLastRecordNum(long lastRecordNum, int count) {
        if (lastRecordNum > count) {
            log.info("ERROR:请求参数lastRecordNum大于总条数");
        }
    }

}
