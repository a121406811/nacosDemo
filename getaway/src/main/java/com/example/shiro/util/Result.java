package com.example.shiro.util;


import java.util.HashMap;
import java.util.Map;

public class Result {

    public static Map<String, Object> err(String message) {
        Map<String, Object> data = new HashMap<>();
        data.put("status", "error");
        data.put("message", message);
        return data;
    }

    public static Map<String, Object> success(int code, Object data) {
        Map<String, Object> redata = new HashMap<>();
        redata.put("status", code);
        redata.put("data", data);
        return redata;
    }
}
