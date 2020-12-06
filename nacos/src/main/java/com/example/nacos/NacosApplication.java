package com.example.nacos;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableDiscoveryClient
@SpringBootApplication
@RefreshScope
public class NacosApplication {

    @Value("${name}")
    private String name;

    public static void main(String[] args) {
        SpringApplication.run(NacosApplication.class, args);
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
