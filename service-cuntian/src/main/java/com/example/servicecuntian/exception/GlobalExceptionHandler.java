package com.example.servicecuntian.exception;

import com.example.servicecuntian.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
    public Map<String, Object> exceptionHandler(HttpServletRequest httpServletRequest, Exception e) {
        logger.error("服务错误:", e);
        return Result.err("服务错误");
    }

    /**
     * 处理 BusinessException 异常
     */
    @ResponseBody
    @ExceptionHandler(value = BusinessException.class)
    public Map<String, Object> businessExceptionHandler(HttpServletRequest httpServletRequest, BusinessException e) {
        logger.error("业务异常；code:" + e.getCode() + "；msg:" + e.getMsg());
        return Result.err(e.getMsg());
    }

    /**
     * 描述：捕获 NullPointerException 异常
     */
    @ExceptionHandler(value = {NullPointerException.class})
    public Map<String, Object> nullPointerExceptionHandle(HttpServletRequest httpServletRequest, NullPointerException e) {
        logger.error("空指针异常；code:", e);
        return Result.err("空指针异常");
    }
}