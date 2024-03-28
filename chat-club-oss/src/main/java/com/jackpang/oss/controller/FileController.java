package com.jackpang.oss.controller;

import com.jackpang.oss.adapter.FileService;
import com.jackpang.oss.entity.Result;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import org.springframework.web.multipart.MultipartFile;

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

    @RequestMapping("/getUrl")
    public String getUrl(String bucketName, String objectName) throws Exception {
        return fileService.getUrl(bucketName, objectName);
    }

    @RequestMapping("/upload")
    public Result upload(MultipartFile uploadFile,
                         String bucketName,
                         String objectName) throws Exception {

        String url = fileService.uploadFile(uploadFile, bucketName, objectName);
        return Result.ok(url);
    }
}
