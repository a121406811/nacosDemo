package com.example.shiro.security;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


/**
 * 此处用于从请求的信息中载入验证信息（即将header中的token包装成Authentication并进行验证）
 */
@Component
public class SecurityContextRepository implements ServerSecurityContextRepository {

    @Autowired
    private MyReactiveAuthenticationManager myReactiveAuthenticationManager;

    @Override
    public Mono<Void> save(ServerWebExchange serverWebExchange, SecurityContext securityContext) {
        return Mono.empty();
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange serverWebExchange) {
        ServerHttpRequest request = serverWebExchange.getRequest();
        String access_token = request.getHeaders().getFirst("access_token");

        Authentication auth = null;

        if (!StringUtils.isBlank(access_token)) {
            auth = new UsernamePasswordAuthenticationToken(access_token, access_token);  //这里存储token
            return this.myReactiveAuthenticationManager.authenticate(auth).map(SecurityContextImpl::new);
        } else {
            return Mono.empty();
        }
    }

}