package com.example.shiro.controller;

import com.example.shiro.util.CASUtil;
import com.example.shiro.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequestMapping("/auth")
@RestController
public class LoginController {

    @Autowired
    private CASUtil casUtil;

    @GetMapping(value = "/login")
    public Map<String, Object> login(@RequestHeader("username") String username, @RequestHeader("password") String password) {
        log.info("--------------用户{}进入登录方法-------------", username);
        ResponseEntity<String> casToken = casUtil.getCasToken(username, password);
        log.info("--------------访问CAS服务器成功-------------", username);
        int status = casToken.getStatusCodeValue();
        String body = casToken.getBody();
        Map<String, Object> result = null;
        if (status == 200) {
            Map<String, String> map = new HashMap<>();
            map.put(body.split("&")[0].split("=")[0], body.split("&")[0].split("=")[1]);
            map.put(body.split("&")[1].split("=")[0], body.split("&")[1].split("=")[1]);
            result = Result.success(casToken.getStatusCodeValue(), map);
        } else {
            log.error("用户{}在CAS登录失败", username);
            result = Result.success(casToken.getStatusCodeValue(), casToken.getStatusCode());
        }
        return result;
    }
}
