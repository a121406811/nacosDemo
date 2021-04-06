package com.example.servicecuntian.util;

import java.util.HashMap;
import java.util.Map;

public class Result {

    public static Map<String, Object> error(String code, String message) {
        Map<String, Object> data = new HashMap<>();
        data.put("resultCode", code);
        data.put("data", message);
        return data;
    }

/*    public static Map<String, Object> success(int code, Object data) {
        Map<String, Object> redata = new HashMap<>();
        redata.put("status", code);
        redata.put("data", data);
        return redata;
    }*/

    public static Map<String, Object> success(int totalDataCount, int currentDataCount, Object data) {
        Map<String, Object> redata = new HashMap<>();
        redata.put("resultCode", "200");
        redata.put("request_body", data);
        redata.put("totalDataCount", totalDataCount);
        redata.put("currentDataCount", currentDataCount);
        return redata;
    }
}
