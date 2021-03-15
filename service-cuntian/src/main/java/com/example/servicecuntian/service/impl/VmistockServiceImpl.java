package com.example.servicecuntian.service.impl;

import com.example.servicecuntian.dao.VmistockDao;
import com.example.servicecuntian.model.Vmistock;
import com.example.servicecuntian.service.ServiceExceptionUtil;
import com.example.servicecuntian.service.VmistockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VmistockServiceImpl implements VmistockService {
    @Autowired
    private VmistockDao vmistockDao;

    @Autowired
    private ServiceExceptionUtil serviceExceptionUtil;

    @Override
    public List<Vmistock> getVmistocks(int startNum, int pageNum) {
        List<Vmistock> vmistocks = null;
        try {
            vmistocks = vmistockDao.getVmistocks(startNum, pageNum);
        } catch (Exception e) {
            serviceExceptionUtil.catchContent(e);
        }
        return vmistocks;
    }

    @Override
    public int getCount() {
        int count = 0;
        try {
            count = vmistockDao.getCount();
        } catch (Exception e) {
            serviceExceptionUtil.catchContent(e);
        }
        return count;
    }
}
