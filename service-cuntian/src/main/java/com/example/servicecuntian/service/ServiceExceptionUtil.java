package com.example.servicecuntian.service;

import com.example.servicecuntian.exception.BusinessException;
import com.example.servicecuntian.service.impl.StockServiceImpl;
import com.example.servicecuntian.util.EmailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 捕捉异常之后进行处理
 */
@Component
public class ServiceExceptionUtil {

    private static final Logger logger = LoggerFactory.getLogger(StockServiceImpl.class);

    @Autowired
    private EmailUtil emailUtil;

    public void catchContent(Exception e) {
        System.out.println(e);
        logger.error("------------查询数据库失败！----------------");
        logger.error(String.valueOf(e));
        emailUtil.sendTemplateEmail("lp10050@qq.com", "查询数据库失败", String.valueOf(e));
        throw new BusinessException("500", "查询数据库失败");
    }
}
