package dev.xxj.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class CustomFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获得路由
        Route route = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
        // 配置文件中配置的route的uri属性(匹配到的route),本例中是http://127.0.0.1:8001
        String uri = route.getUri().toString();

        ServerHttpRequest request = exchange.getRequest();
        // 请求路径中域名之后的部分,本例中是/api/name/get
        String path = request.getPath().toString();

        // 转发后的完整地址,本例中是http://127.0.0.1:8001/api/name/get
        String address = uri + path;

        log.warn("uri: {}", uri);
        log.warn("path: {}", path);
        log.warn("转发后的完整地址 address: {}", address);
        return chain.filter(exchange);
    }

}