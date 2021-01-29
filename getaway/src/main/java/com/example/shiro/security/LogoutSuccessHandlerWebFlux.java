package com.example.shiro.security;


import com.example.shiro.util.RestTemplateUtil;
import com.example.shiro.util.Result;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * 成功登出时调用的自定义处理类<br>
 * 由于SpringGateWay基于WebFlux，所以SpringSecruity很多原有写法，都得改为WebFlux的方式才能生效！
 */
@Component
public class LogoutSuccessHandlerWebFlux implements ServerLogoutSuccessHandler {

    @Override
    public Mono<Void> onLogoutSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
        Map<String, Object> success = Result.success(0, "成功登出！");
        return RestTemplateUtil.outPut(success, webFilterExchange.getExchange().getResponse());
    }
}
