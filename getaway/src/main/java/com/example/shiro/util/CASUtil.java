package com.example.shiro.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class CASUtil {

    private static final RestTemplate restTemplate = new RestTemplate();
    private static ResponseEntity<String> result = null;

    @Value("${CAS.getTokenUrl}")
    private String getTokenUrl;

    @Value("${CAS.getIdUrl}")
    private String getIdUrl;

    @Value("${CAS.client_id}")
    private String client_id;

    /**
     * 调用CAS，验证账户密码
     *
     * @param username
     * @param password
     * @return
     */
    public ResponseEntity<String> getCasToken(String username, String password) {
        MultiValueMap<String, String> requestParam = new LinkedMultiValueMap<>();
        requestParam.set("grant_type", "password");
        requestParam.set("client_id", client_id);
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
     *
     * @param token
     * @return
     */
    public ResponseEntity<String> getUserIdToToken(String token) {

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