package com.example.servicecuntian.dao;

import com.example.servicecuntian.model.Vmistock;

import java.util.List;

public interface VmiStockDao {

    List<Vmistock> getVmiStocks(int startNum, int pageNum) throws Exception;

    int getCount() throws Exception;

    String getLatestDateMark() throws Exception;

}
