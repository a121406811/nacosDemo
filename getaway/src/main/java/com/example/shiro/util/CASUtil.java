package com.example.shiro.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class CASUtil {

    private static final String getTokenUrl = "https://sso.szhq.com/ssocas/oauth2.0/accessToken";
    private static final String getIdUrl = "https://sso.szhq.com/ssocas/oauth2.0/profile";
    private static RestTemplate restTemplate = new RestTemplate();
    private static ResponseEntity<String> result = null;

    /**
     *  调用CAS，验证账户密码
     * @param username
     * @param password
     * @return
     */
    public static ResponseEntity<String> getCasToken(String username, String password) {
        MultiValueMap<String, String> requestParam = new LinkedMultiValueMap<>();
        requestParam.set("grant_type", "password");
        requestParam.set("client_id", "c86c43b1efec472b41839dd78ce87c94");
        requestParam.set("username", username);
        requestParam.set("password", password);

        try {
            result = restTemplate.postForEntity(getTokenUrl, requestParam, String.class);
        } catch (Exception e) {
            //获取状态码返回
            int code = Integer.parseInt(e.toString().split(":")[1].trim());
            result = new ResponseEntity(HttpStatus.valueOf(code));
        }

        return result;
    }

    /**
     * 调用CAS  验证token
     * @param token
     * @return
     */
    public static ResponseEntity<String> getUserIdToToken(String token) {

        try {
//            result = restTemplate.getForEntity(getIdUrl, String.class, requestParam);
            result = restTemplate.getForEntity(getIdUrl + "?access_token=" + token + "&expires_in=28800", String.class);
        } catch (Exception e) {
            int code = Integer.parseInt(e.toString().split(":")[1].trim());
            result = new ResponseEntity(HttpStatus.valueOf(code));
        }
        return result;
    }
}