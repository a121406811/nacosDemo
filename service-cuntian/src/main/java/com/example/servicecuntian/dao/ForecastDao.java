package com.example.servicecuntian.dao;

import com.example.servicecuntian.model.Forecast;

import java.util.List;

public interface ForecastDao {

    List<Forecast> getForecasts(Integer startNum, Integer pageNum) throws Exception;

    Integer getCount() throws Exception;

    String getLatestDateMark() throws Exception;

}
