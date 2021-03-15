package com.example.servicecuntian.exception;

import com.example.servicecuntian.util.Result;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Map;

/**
 * 全局异常处理器
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理 Exception 异常
     *
     * @param httpServletRequest httpServletRequest
     * @param e                  异常
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Map<String, Object> exceptionHandler(HttpServletRequest httpServletRequest, HttpResponse httpResponse, Exception e) {
        logger.error("服务错误:", e);
        return Result.error("500", e.toString());
    }

    /**
     * 处理 BusinessException 异常
     */
    @ResponseBody
    @ExceptionHandler(value = BusinessException.class)
    public Map<String, Object> businessExceptionHandler(HttpServletRequest httpServletRequest, BusinessException e) {
        logger.error("业务异常；code:" + e.getCode() + "；msg:" + e.getMsg());
        return Result.error(e.getCode(), e.getMsg());
    }


    /**
     * 描述：捕获 UncategorizedSQLException 异常
     * 查询kylin失败
     */
    @ExceptionHandler(value = {UncategorizedSQLException.class})
    public Map<String, Object> uncategorizedSQLException(HttpServletRequest httpServletRequest, UncategorizedSQLException e) {
        logger.error("SQL语句查询失败！", e);
        return Result.error("500", "SQL语句查询失败！");
    }


    /*    *//**
     * 描述：kylin连接出错！
     * 查询kylin失败
     *//*
    @ExceptionHandler(value = {TranslateException.class})
    public Map<String, Object> translateException(HttpServletRequest httpServletRequest, TranslateException e) {
        logger.error("kylin连接出错！", e);
        return Result.err("kylin连接出错！");
    }*/

}