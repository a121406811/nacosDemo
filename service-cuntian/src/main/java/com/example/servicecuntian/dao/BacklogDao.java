package com.example.servicecuntian.dao;

import com.example.servicecuntian.model.Backlog;

import java.util.List;

public interface BacklogDao {

    List<Backlog> getBacklogs(Integer startNum, Integer pageNum) throws Exception;

    Integer getCount() throws Exception;

    String getLatestDateMark() throws Exception;

}
