package com.example.test.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(value = "测试接口")
@RequestMapping("/test")
@RestController
@RefreshScope
public class TestController {

    @Value("${name}")
    private String name;

    @ApiOperation(value = "test")     //这个注解必须要
    @GetMapping("/test")
    public String test() {
        return name;
    }
}