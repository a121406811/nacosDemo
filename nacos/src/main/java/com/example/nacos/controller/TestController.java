package com.example.nacos.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "测试接口", tags = "测试" )
@RequestMapping("/test")
@RestController
public class TestController {

    @Value("${name}")
    private String name;

    @ApiOperation(value = "hello")
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }


    @GetMapping("/helloNacos")
    public String helloNacos() {
        return name;
    }

    @GetMapping("/test")
    public String test() {
        return "nacos:" + name+"1111";
    }

}
