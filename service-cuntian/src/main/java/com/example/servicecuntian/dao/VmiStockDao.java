package com.example.servicecuntian.dao;

import com.example.servicecuntian.model.Vmistock;

import java.util.List;

public interface VmiStockDao {

    public List<Vmistock> getVmiStocks(int startNum, int pageNum) throws Exception;

    public int getCount() throws Exception;

    public String getLatestDateMark() throws Exception;

}
