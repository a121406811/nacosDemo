package com.example.servicecuntian.service;

import com.example.servicecuntian.model.Forecast;

import java.util.List;

public interface ForecastService {

    List<Forecast> getForecasts(Integer startNum, Integer pageNum);

    Integer getCount();

    String getLatestDateMark();
}
