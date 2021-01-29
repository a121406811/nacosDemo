package com.example.shiro.security;


import com.example.shiro.util.RestTemplateUtil;
import com.example.shiro.util.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.authentication.HttpBasicServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * 未登录访问资源时的处理类，若无此处理类，前端页面会弹出登录窗口<br>
 * 由于SpringGateWay基于WebFlux，所以SpringSecruity很多原有写法，都得改为WebFlux的方式才能生效！
 */
@Component
public class CustomHttpBasicServerAuthenticationEntryPointWebFlux extends HttpBasicServerAuthenticationEntryPoint /* implements ServerAuthenticationEntryPoint */ {

    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException e) {
        Map<String, Object> success = Result.success(500, "您没有携带身份凭证！");
        return RestTemplateUtil.outPut(success, exchange.getResponse());
    }
}
