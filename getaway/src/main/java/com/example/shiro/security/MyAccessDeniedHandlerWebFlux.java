package com.example.shiro.security;


import com.example.shiro.util.RestTemplateUtil;
import com.example.shiro.util.Result;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * 无权限访问被拒绝时的自定义处理器。如不自己处理，默认返回403错误<br>
 * 由于SpringGateWay基于WebFlux，所以SpringSecruity很多原有写法，都得改为WebFlux的方式才能生效！
 */
@Component
public class MyAccessDeniedHandlerWebFlux implements ServerAccessDeniedHandler {
    @Override
    public Mono<Void> handle(ServerWebExchange serverWebExchange, AccessDeniedException e) {
        Map<String, Object> success = Result.success(500, "您无此资源的访问权限！");
        return RestTemplateUtil.outPut(success, serverWebExchange.getResponse());
    }
}
