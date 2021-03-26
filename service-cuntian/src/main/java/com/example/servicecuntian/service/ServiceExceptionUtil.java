package com.example.servicecuntian.service;

import com.example.servicecuntian.exception.BusinessException;
import com.example.servicecuntian.util.EmailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 捕捉异常之后进行处理
 */
@Component
public class ServiceExceptionUtil {

    private static final Logger logger = LoggerFactory.getLogger(ServiceExceptionUtil.class);

    @Autowired
    private EmailUtil emailUtil;

    @Value("${kylinEmail}")
    private List<String> emails;

    public void catchContent(Exception e) {
        System.out.println(e);
        // 获取调用本方法的对象
        StackTraceElement stack[] = Thread.currentThread().getStackTrace();
        String tableName = stack[2].toString();
        System.out.println(tableName);//调用本方法的类       com.test.B.main(B.java:4)
        logger.error("------------" + tableName + "查询数据库失败！----------------");
        logger.error(String.valueOf(e));
        emailUtil.sendTemplateEmail(emails, tableName + "查询数据库失败", String.valueOf(e));
        throw new BusinessException("500", tableName + "查询数据库失败");
    }
}
