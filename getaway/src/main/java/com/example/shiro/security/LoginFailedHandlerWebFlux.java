package com.example.shiro.security;


import com.example.shiro.util.RestTemplateUtil;
import com.example.shiro.util.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * 登录失败时调用的自定义处理类
 * 由于SpringGateWay基于WebFlux，所以SpringSecruity很多原有写法，都得改为WebFlux的方式才能生效！
 */
@Component
public class LoginFailedHandlerWebFlux implements ServerAuthenticationFailureHandler {
    @Override
    public Mono<Void> onAuthenticationFailure(WebFilterExchange webFilterExchange, AuthenticationException e) {
        Map<String, Object> success = Result.success(500, e.getMessage());
        return RestTemplateUtil.outPut(success, webFilterExchange.getExchange().getResponse());
    }
}
