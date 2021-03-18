package com.example.servicecuntian.dao;

import com.example.servicecuntian.model.Vmistock;

import java.util.List;

public interface VmistockDao {

    public List<Vmistock> getVmistocks(int startNum, int pageNum) throws Exception;

    public int getCount() throws Exception;

    public String getLatestDateMark() throws Exception;

}
