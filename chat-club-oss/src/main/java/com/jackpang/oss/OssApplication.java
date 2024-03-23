package com.jackpang.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * description: OssApplication
 * date: 3/9/24 7:49 PM
 * author: jinhao_pang
 * version: 1.0
 */
//@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan("com.jackpang")
public class OssApplication {

    public static void main(String[] args) {
        SpringApplication.run(OssApplication.class);
    }
}
