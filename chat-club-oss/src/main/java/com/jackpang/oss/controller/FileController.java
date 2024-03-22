package com.jackpang.oss.controller;

import com.jackpang.oss.adapter.FileService;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * description: FileController
 * date: 3/20/24 5:18 PM
 * author: jinhao_pang
 * version: 1.0
 */
@RestController
public class FileController {
    @Resource
    private FileService fileService;
//    @NacosValue(value = "${storage.service.type}", autoRefreshed = true)
//    private String storageType;
//    @Value(value = "${storage.service.type}")
//    private String storageType;

    @RequestMapping("/testGetAllBuckets")
    @SneakyThrows
    public String testGetAllBuckets() throws Exception {
        List<String> allBucket = fileService.getAllBucket();
        return allBucket.get(0);
    }

//    @RequestMapping("/testNacos")
//    public String testNacos() throws Exception {
//        return storageType;
//    }
//    @Value(value = "${storage.service.type}")
//    private String useLocalCache;
//
//    @RequestMapping("/test")
//    public String test() {
//        String useLocalCache1 = useLocalCache;
//        return useLocalCache1;
//    }
}
