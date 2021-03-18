package com.example.servicecuntian.service;

import com.example.servicecuntian.model.Vmistock;

import java.util.List;

public interface VmistockService {

    public List<Vmistock> getVmistocks(int startNum, int pageNum);

    public int getCount();

    public String getLatestDateMark();
}
