//package com.example.shiro.filter;
//
//import org.springframework.cloud.gateway.filter.GatewayFilter;
//import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
//import org.springframework.cloud.gateway.filter.factory.SetStatusGatewayFilterFactory;
//import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//@Component
//public class SetStatusFilter extends AbstractGatewayFilterFactory<SetStatusFilter.Config> {
////    @Override
////    public GatewayFilter apply(SetStatusGatewayFilterFactory.Config config) {
////        final HttpStatus status = ServerWebExchangeUtils.parse(config.status);
////        return (exchange, chain) -> {
////            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
////                // check not really needed, since it is guarded in setStatusCode,
////                // but it's a good example
////                if (!exchange.getResponse().isCommitted()) {
////                    setResponseStatus(exchange, status);
////                }
////            }));
////        };
////    }
//
//    private void setResponseStatus(ServerWebExchange exchange, HttpStatus status) {
//    }
//
//    @Override
//    public GatewayFilter apply(SetStatusFilter.Config config) {
//        return null;
//    }
//}