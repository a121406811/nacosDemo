package com.example.nacos.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(value = "测试接口")
@RequestMapping("/nacos")
@RestController
@RefreshScope
public class TestController {

    @Value("${name}")
    private String name;

//    @ApiOperation(value = "这是一个hello接口", notes = "接口发布说明")
//    @GetMapping("/hello")
//    public String hello() {
//        return "hello";
//    }
//

    @ApiOperation(value = "这是一个hello接口")     //这个注解必须要
    @GetMapping("/test")
    public String test() {
        return "nacos:" + name + "1111";
    }
    
}