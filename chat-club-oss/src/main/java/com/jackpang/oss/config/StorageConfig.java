package com.jackpang.oss.config;

import com.jackpang.oss.adapter.StorageAdapter;
import com.jackpang.oss.adapter.AliStorageAdapter;
import com.jackpang.oss.adapter.MinioStorageAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * description: StorageConfig
 * date: 3/20/24 6:27 PM
 * author: jinhao_pang
 * version: 1.0
 */
@Configuration
public class StorageConfig {
    @Value("${storage.service.type}")
    private String storageType;

    @Bean
    public StorageAdapter storageService() {
        if ("minio".equals(storageType)) {
            return new MinioStorageAdapter();
        } else if ("aliyun".equals(storageType)) {
            return new AliStorageAdapter();
        } else {
            throw new IllegalArgumentException("no file storage handler found");
        }
    }
}
