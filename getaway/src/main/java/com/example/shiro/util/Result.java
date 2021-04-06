package com.example.shiro.util;


import java.util.HashMap;
import java.util.Map;

public class Result {

    public static Map<String, Object> err(String message) {
        Map<String, Object> data = new HashMap<>();
        data.put("resultCode", "500");
        data.put("data", message);
        return data;
    }

    public static Map<String, Object> success(int code, Object data) {
        Map<String, Object> redata = new HashMap<>();
        redata.put("resultCode", code);
        redata.put("data", data);
        return redata;
    }
}
