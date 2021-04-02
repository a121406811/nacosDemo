package com.example.servicecuntian.service;

import com.example.servicecuntian.model.Vmistock;

import java.util.List;

public interface VmiStockService {

    List<Vmistock> getVmiStocks(int startNum, int pageNum);

    int getCount();

    String getLatestDateMark();
}
