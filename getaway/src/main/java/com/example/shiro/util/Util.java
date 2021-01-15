package com.example.shiro.util;


import org.json.JSONObject;

public class Util {

    public static String jsonGetValue(String jsonStr,String name) throws Exception {
        JSONObject a = new JSONObject(jsonStr);
        return (String) a.get(name);
    }
}
