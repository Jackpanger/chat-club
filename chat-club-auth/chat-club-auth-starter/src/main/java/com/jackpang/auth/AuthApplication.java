package com.jackpang.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * description: SubjectApplication
 * date: 3/9/24 7:49 PM
 * author: jinhao_pang
 * version: 1.0
 */
@SpringBootApplication
@ComponentScan("com.jackpang")
@MapperScan("com.jackpang.**.mapper")
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class);
    }
}
