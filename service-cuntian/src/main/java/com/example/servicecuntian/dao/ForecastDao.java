package com.example.servicecuntian.dao;

import com.example.servicecuntian.model.Forecast;

import java.util.List;

public interface ForecastDao {

    public List<Forecast> getForecasts(Integer startNum, Integer pageNum);

    public Integer getCount();
}
