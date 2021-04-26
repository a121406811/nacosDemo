package com.example.servicecuntian.service.impl;

import com.example.servicecuntian.dao.BacklogDao;
import com.example.servicecuntian.dao.ForecastDao;
import com.example.servicecuntian.model.Backlog;
import com.example.servicecuntian.model.Forecast;
import com.example.servicecuntian.service.BacklogService;
import com.example.servicecuntian.service.ServiceExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BacklogServiceImpl implements BacklogService {
    @Autowired
    private BacklogDao backlogDao;

    @Autowired
    private ServiceExceptionUtil serviceExceptionUtil;

    @Override
    public List<Backlog> getBacklogs(Integer startNum, Integer pageNum) {
        List<Backlog> backlogs = null;
        try {
            backlogs = backlogDao.getBacklogs(startNum, pageNum);
        } catch (Exception e) {
            serviceExceptionUtil.catchContent(e);
        }
        return backlogs;
    }

    @Override
    public Integer getCount() {
        Integer count = 0;
        try {
            count = backlogDao.getCount();
        } catch (Exception e) {
            serviceExceptionUtil.catchContent(e);
        }
        return count;
    }

    @Override
    public String getLatestDateMark() {
        String latestDateMark = null;
        try {
            latestDateMark = backlogDao.getLatestDateMark();
        } catch (Exception e) {
            serviceExceptionUtil.catchContent(e);
        }
        return latestDateMark;
    }
}
