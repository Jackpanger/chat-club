package com.jackpang.wechat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * description: WechatApplication
 * date: 3/9/24 7:49 PM
 * author: jinhao_pang
 * version: 1.0
 */
@SpringBootApplication
@ComponentScan("com.jackpang")
public class WechatApplication {

    public static void main(String[] args) {
        SpringApplication.run(WechatApplication.class);
    }
}
