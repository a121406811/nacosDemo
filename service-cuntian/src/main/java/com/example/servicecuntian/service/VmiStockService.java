package com.example.servicecuntian.service;

import com.example.servicecuntian.model.Vmistock;

import java.util.List;

public interface VmiStockService {

    public List<Vmistock> getVmiStocks(int startNum, int pageNum);

    public int getCount();

    public String getLatestDateMark();
}
