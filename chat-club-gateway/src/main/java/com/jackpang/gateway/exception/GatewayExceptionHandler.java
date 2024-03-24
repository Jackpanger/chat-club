package com.jackpang.gateway.exception;

import cn.dev33.satoken.exception.SaTokenException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jackpang.gateway.entity.Result;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * description: GatewayExceptionHandler
 * date: 3/23/24 7:09 PM
 * author: jinhao_pang
 * version: 1.0
 */
@Component
public class GatewayExceptionHandler implements ErrorWebExceptionHandler {
    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public Mono<Void> handle(ServerWebExchange serverWebExchange, Throwable throwable) {
        ServerHttpRequest request = serverWebExchange.getRequest();
        ServerHttpResponse response = serverWebExchange.getResponse();
        Integer code = 200;
        String message = "";
        if (throwable instanceof SaTokenException) {
            code = 401;
            message = " User no permission";
        } else {
            code = 500;
            message = "Server busy";
        }
        Result result = Result.fail(code, message);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        return response.writeWith(Mono.fromSupplier(()->{
            DataBufferFactory dataBufferFactory = response.bufferFactory();
            byte[] bytes = null;
            try {
                bytes = objectMapper.writeValueAsBytes(result);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return dataBufferFactory.wrap(bytes);
        }));
    }
}
