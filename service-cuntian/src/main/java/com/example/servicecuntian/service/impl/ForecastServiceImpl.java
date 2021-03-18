package com.example.servicecuntian.service.impl;

import com.example.servicecuntian.dao.ForecastDao;
import com.example.servicecuntian.model.Forecast;
import com.example.servicecuntian.service.ForecastService;
import com.example.servicecuntian.service.ServiceExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForecastServiceImpl implements ForecastService {
    @Autowired
    private ForecastDao forecastDao;

    @Autowired
    private ServiceExceptionUtil serviceExceptionUtil;

    @Override
    public List<Forecast> getForecasts(Integer startNum, Integer pageNum) {
        List<Forecast> forecasts = null;
        try {
            forecasts = forecastDao.getForecasts(startNum, pageNum);
        } catch (Exception e) {
            serviceExceptionUtil.catchContent(e);
        }
        return forecasts;
    }

    @Override
    public Integer getCount() {
        Integer count = 0;
        try {
            count = forecastDao.getCount();
        } catch (Exception e) {
            serviceExceptionUtil.catchContent(e);
        }
        return count;
    }

    @Override
    public String getLatestDateMark() {
        String latestDateMark = null;
        try {
            latestDateMark = forecastDao.getLatestDateMark();
        } catch (Exception e) {
            serviceExceptionUtil.catchContent(e);
        }
        return latestDateMark;
    }
}
