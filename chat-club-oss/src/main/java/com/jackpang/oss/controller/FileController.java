package com.jackpang.oss.controller;

import com.jackpang.oss.adapter.FileService;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("/testGetAllBuckets")
    @SneakyThrows
    public String testGetAllBuckets() throws Exception {
        List<String> allBucket = fileService.getAllBucket();
        return allBucket.get(0);
    }
}
