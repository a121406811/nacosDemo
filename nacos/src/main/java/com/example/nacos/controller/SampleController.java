package com.example.nacos.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RefreshScope  //打开动态刷新功能
public class SampleController {

    @Value("${name}")
    String userName;

    @Value("${age}")
    int age;

    @RequestMapping("/get")
    public String get() {
        return userName + age;
    }

}