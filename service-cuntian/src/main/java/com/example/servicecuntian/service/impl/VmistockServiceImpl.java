package com.example.servicecuntian.service.impl;

import com.example.servicecuntian.dao.VmistockDao;
import com.example.servicecuntian.model.Vmistock;
import com.example.servicecuntian.service.VmistockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VmistockServiceImpl implements VmistockService {
    @Autowired
    private VmistockDao vmistockDao;

    @Override
    public List<Vmistock> getVmistocks(int startNum, int pageNum) {
        return vmistockDao.getVmistocks(startNum, pageNum);
    }

    @Override
    public int getCount() {
        return vmistockDao.getCount();
    }
}
