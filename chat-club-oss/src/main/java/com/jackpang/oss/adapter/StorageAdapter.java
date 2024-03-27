package com.jackpang.oss.adapter;

import com.jackpang.oss.entity.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * description: StorageAdapter
 * date: 3/20/24 5:23 PM
 * author: jinhao_pang
 * version: 1.0
 */
public interface StorageAdapter {

    void createBucket(String bucketName);

    void uploadFile(MultipartFile uploadFile, String bucketName, String objectName);

    List<String> getAllBucket();

    List<FileInfo> getAllFile(String bucketName);

    InputStream download(String bucketName, String objectName);


    void deleteBucket(String bucketName);

    void deleteFile(String bucketName, String objectName);

    String getUrl(String bucketName, String objectName);
}
