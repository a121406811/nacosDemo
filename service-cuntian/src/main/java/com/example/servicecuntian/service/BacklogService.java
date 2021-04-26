package com.example.servicecuntian.service;

import com.example.servicecuntian.model.Backlog;

import java.util.List;

public interface BacklogService {

    List<Backlog> getBacklogs(Integer startNum, Integer pageNum);

    Integer getCount();

    String getLatestDateMark();
}
