package com.example.servicecuntian.exception;

import lombok.Data;

/**
 * 自定义业务异常类
 */

@Data
public class BusinessException extends RuntimeException {
    private String code;
    private String msg;

    public BusinessException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}