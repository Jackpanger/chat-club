package com.jackpang.oss;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.concurrent.TimeUnit;

/**
 * description: OssApplication
 * date: 3/9/24 7:49 PM
 * author: jinhao_pang
 * version: 1.0
 */
@SpringBootApplication
@ComponentScan("com.jackpang")
public class OssApplication {

    public static void main(String[] args) {
        SpringApplication.run(OssApplication.class);
    }
}
