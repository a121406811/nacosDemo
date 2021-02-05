package com.example.shiro.security;

import com.example.shiro.dao.RoleDao;
import com.example.shiro.domain.Permission;
import com.example.shiro.domain.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义的鉴权服务，通过鉴权的才能继续访问某个请求<br>
 * 由于SpringGateWay基于WebFlux，所以SpringSecruity很多原有写法，都得改为WebFlux的方式才能生效！
 */
@Slf4j
@Component
public class MyReacServiceWebFlux implements ReactiveAuthorizationManager<AuthorizationContext> {


    @Autowired
    private RoleDao roleDao;


    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
        ServerHttpRequest request = authorizationContext.getExchange().getRequest();
        String path = request.getURI().getPath();
        PathMatcher pathMatcher = new AntPathMatcher();

        // token为空拒绝访问
//        String token = request.getHeaders().getFirst("access_token");
//        if (StringUtils.isBlank(token)) {
//            return Mono.just(new AuthorizationDecision(false));
//        }

        // 缓存取资源权限角色关系列表
//        String username = request.getQueryParams().getFirst("username");
//        SecurityContext context = SecurityContextHolder.getContext();
//        User user = userDao.findUserByUserId(username);

//        if (user == null) {
//            return Mono.just(new AuthorizationDecision(false));
//        }

        // 请求路径匹配到的资源需要的角色权限集合authorities统计
        List<String> authorities = new ArrayList<>();
        List<Role> all = roleDao.findAll();
        for (Role role : roleDao.findAll()) {
            for (Permission permission : role.getPermissions()) {
                if (pathMatcher.match(permission.getName(), path)) {
                    authorities.add(role.getName());
                }
            }
        }

        Mono<AuthorizationDecision> authorizationDecisionMono = mono
                .filter(Authentication::isAuthenticated)
                .flatMapIterable(Authentication::getAuthorities)
                .map(GrantedAuthority::getAuthority)
                .any(roleId -> {
                    // roleId是请求用户的角色(格式:ROLE_{roleId})，authorities是请求资源所需要角色的集合
                    log.info("访问路径：{}", path);
                    log.info("用户角色roleId：{}", roleId);
                    log.info("资源需要权限authorities：{}", authorities);
                    System.out.println("访问路径：{}" + path);
                    System.out.println("用户角色roleId：{}" + roleId);
                    System.out.println("资源需要权限authorities：{}" + authorities);
                    boolean contains = authorities.contains(roleId);
                    System.out.println(contains);
                    return contains;
                })
                .map(AuthorizationDecision::new)
                .defaultIfEmpty(new AuthorizationDecision(false));

        return authorizationDecisionMono;
    }

}
