package com.example.servicecuntian.service;

import com.example.servicecuntian.model.Forecast;

import java.util.List;

public interface ForecastService {

    public List<Forecast> getForecasts(Integer startNum, Integer pageNum);

    public Integer getCount();

    public String getLatestDateMark();
}
