package com.example.shiro.security;

import com.example.shiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
public class SecurityConfig {
    @Autowired
    private UserService userService;

    //自定义的鉴权服务，通过鉴权的才能继续访问某个请求
    @Autowired
    private MyReacServiceWebFlux myReacServiceWebFlux;

    //无权限访问被拒绝时的自定义处理器。如不自己处理，默认返回403错误
    @Autowired
    private MyAccessDeniedHandlerWebFlux myAccessDeniedHandlerWebFlux;

    //登录成功时调用的自定义处理类
    @Autowired
    private LoginSuccessHandlerWebFlux loginSuccessHandlerWebFlux;

    //登录失败时调用的自定义处理类
    @Autowired
    private LoginFailedHandlerWebFlux loginFailedHandlerWebFlux;

    //成功登出时调用的自定义处理类
    @Autowired
    private LogoutSuccessHandlerWebFlux logoutSuccessHandlerWebFlux;

    //未登录访问资源时的处理类，若无此处理类，前端页面会弹出登录窗口
    @Autowired
    private CustomHttpBasicServerAuthenticationEntryPointWebFlux customHttpBasicServerAuthenticationEntryPoint;

    @Autowired
    private MyReactiveAuthenticationManager myReactiveAuthenticationManager;

    @Autowired
    private SecurityContextRepository securityContextRepository;

    //security的鉴权排除列表
    private static final String[] excludedAuthPages = {
            "/auth/login",
            "/auth/logout",
            "/health",
            "/api/socket/**",
            "/v2/api-docs",
            "/configuration/ui",
            "/swagger-resources",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/swagger-resources/configuration/ui",
            "/swagger-ui.html",
            "/webjars/springfox-swagger-ui",
            "/webjars/springfox-swagger-ui/**",
            "/swagger/**"
    };

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Bean
    SecurityWebFilterChain webFluxSecurityFilterChain(ServerHttpSecurity http) throws Exception {
        http
                .securityContextRepository(securityContextRepository)
                .authenticationManager(myReactiveAuthenticationManager)//自定义登录验证。自动扫描注入，无需手动注入
                .authorizeExchange()
                .pathMatchers(excludedAuthPages).permitAll()  //无需进行权限过滤的请求路径
                .pathMatchers(HttpMethod.OPTIONS).permitAll() //option 请求默认放行
                .and()
                .authorizeExchange().pathMatchers("/**").access(myReacServiceWebFlux)//自定义的鉴权服务，通过鉴权的才能继续访问某个请求
                .anyExchange().authenticated()
                .and()
                .httpBasic()
                .and()
                .formLogin()
                .loginPage("/auth/login")//指定登录请求路径
                .authenticationSuccessHandler(loginSuccessHandlerWebFlux) //认证成功
                .authenticationFailureHandler(loginFailedHandlerWebFlux) //登陆验证失败
                .and().exceptionHandling().authenticationEntryPoint(customHttpBasicServerAuthenticationEntryPoint)  //未登录访问资源时的处理类，若无此处理类，前端页面会弹出登录窗口
                .and().exceptionHandling().accessDeniedHandler(myAccessDeniedHandlerWebFlux)//访问被拒绝时自定义处理器
                .and().csrf().disable()//必须支持跨域
                .logout().logoutUrl("/auth/logout")

                .logoutSuccessHandler(logoutSuccessHandlerWebFlux);//成功登出时调用的自定义处理类

        return http.build();
    }


    /**
     * BCrypt密码编码
     *
     * @return
     */
    @Bean
    public BCryptPasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


}