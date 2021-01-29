package com.example.shiro.controller;

import com.example.shiro.util.CASUtil;
import com.example.shiro.util.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RequestMapping("/auth")
@RestController
public class LoginController {

    @GetMapping(value = "/login")
    public Map<String, Object> login(@RequestHeader("username") String username, @RequestHeader("password") String password) {
        ResponseEntity<String> casToken = CASUtil.getCasToken(username, password);
        int status = casToken.getStatusCodeValue();
        String body = casToken.getBody();
        Map<String, Object> result = null;
        if (status == 200) {
            Map<String, String> map = new HashMap<String, String>();
            map.put(body.split("&")[0].split("=")[0], body.split("&")[0].split("=")[1]);
            map.put(body.split("&")[1].split("=")[0], body.split("&")[1].split("=")[1]);
            result = Result.success(casToken.getStatusCodeValue(), map);
        } else {
            result = Result.success(casToken.getStatusCodeValue(), casToken.getStatusCode());
        }
        return result;
    }
}
