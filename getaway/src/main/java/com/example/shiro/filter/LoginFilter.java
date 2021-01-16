package com.example.shiro.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.shiro.util.CASUtil;
import com.example.shiro.util.RestTemplateUtil;
import com.example.shiro.util.Result;
import com.example.shiro.util.Util;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义一个全局过滤器
 * 实现 globalfilter , ordered接口
 * 拦截所有请求，使所有请求在访问之前必须登录
 */
@Component
public class LoginFilter implements GlobalFilter, Ordered {


    /**
     * 执行过滤器中的业务逻辑
     * 对请求参数中的access-token进行判断
     * 如果存在此参数:代表已经认证成功
     * 如果不存在此参数 : 认证失败.
     * ServerWebExchange : 相当于请求和响应的上下文(zuul中的RequestContext)
     */
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        System.out.println("执行了自定义的全局过滤器");
//        //1.获取请求参数access-token
////        String token = exchange.getRequest().getQueryParams().getFirst("access-token");
//        ServerHttpRequest request = exchange.getRequest();
//        String token = request.getHeaders().getFirst("access-token");
//        //2.判断token是否存在
//        if (token == null) {
//            // 3.如果不存在 : 判断用户名密码
//            String username = exchange.getRequest().getQueryParams().getFirst("username");
//            String password = exchange.getRequest().getQueryParams().getFirst("password");
//            // 判断用户名或密码是否空
//            if ("".equals(username) || "".equals(password)) {
//                exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
//                return exchange.getResponse().setComplete(); //请求结束
//            } else {
//                // 调用shiro
//                String url = String.format("http://%s/shiro/login" + "?username=" + username + "&password=" + password, "shiro");
//                token = template.getForObject(url, String.class);
//                // 返回token到请求头
//                exchange.getResponse().getHeaders().add("access-token", token);
//                return exchange.getResponse().setComplete(); //请求结束
//            }
//        }
//        //4.如果存在,调用shiro校验
//        //....
//        return chain.filter(exchange); //继续向下执行
//    }
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("执行了自定义的全局过滤器");
        // 如果是swagger请求主页直接放行
        ServerHttpRequest request = exchange.getRequest();
        String url = request.getURI().toString();
        System.out.println("url:" + url);
        if (url.endsWith("/v2/api-docs")) {
            return chain.filter(exchange);
        }

        // 判断用户名密码
        String username = request.getQueryParams().getFirst("username");
        String password = request.getQueryParams().getFirst("password");
        String access_token = request.getHeaders().getFirst("access_token");
        ServerHttpResponse response = exchange.getResponse();
        // 先判断token  在判断账户密码
        if (StringUtils.isBlank(access_token)) {
            if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
                return RestTemplateUtil.outPut(Result.success(400, "请进行身份认证"), response);
            } else {
                // 调用CAS获取token
                // 请求结束  返回token
                ResponseEntity<String> result = CASUtil.getCasToken(username, password);
                String body = result.getBody();
                Map<String, Object> success = null;
                if (StringUtils.isBlank(body)) {
                    success = Result.success(result.getStatusCodeValue(), result.getStatusCode());
                } else {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put(body.split("&")[0].split("=")[0], body.split("&")[0].split("=")[1]);
                    map.put(body.split("&")[1].split("=")[0], body.split("&")[1].split("=")[1]);
                    success = Result.success(result.getStatusCodeValue(), map);
                }
                return RestTemplateUtil.outPut(success, response);
            }
        }
        //调用CAS验证token
        ResponseEntity<String> result = CASUtil.getUserIdToToken(access_token);
        String body = result.getBody();
        String userId = null;
        try {
            userId = Util.jsonGetValue(body, "id");
            return chain.filter(exchange); //继续向下执行
        } catch (Exception e) {
            return RestTemplateUtil.outPut(Result.success(401, "Unauthorized"), response);
        }
    }

    /**
     * 指定过滤器的执行顺序 , 返回值越小,执行优先级越高
     */
    @Override
    public int getOrder() {
        return 2;
    }
}
