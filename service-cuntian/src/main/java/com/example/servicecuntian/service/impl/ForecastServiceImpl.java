package com.example.servicecuntian.service.impl;

import com.example.servicecuntian.dao.ForecastDao;
import com.example.servicecuntian.model.Forecast;
import com.example.servicecuntian.service.ForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForecastServiceImpl implements ForecastService {
    @Autowired
    private ForecastDao forecastDao;

    @Override
    public List<Forecast> getForecasts(Integer startNum, Integer pageNum) {
        return forecastDao.getForecasts(startNum, pageNum);
    }

    @Override
    public Integer getCount() {
        return forecastDao.getCount();
    }
}
