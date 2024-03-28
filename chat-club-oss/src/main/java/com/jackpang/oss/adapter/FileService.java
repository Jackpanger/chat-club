package com.jackpang.oss.adapter;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * description: FileService
 * date: 3/20/24 6:22 PM
 * author: jinhao_pang
 * version: 1.0
 */
@Service
public class FileService {
    private final StorageAdapter storageAdapter;

    public FileService(StorageAdapter storageAdapter) {
        this.storageAdapter = storageAdapter;
    }

    public List<String> getAllBucket() {
        return storageAdapter.getAllBucket();
    }

    public String getUrl(String bucketName, String objectName) {
        return storageAdapter.getUrl(bucketName, objectName);
    }

    public String uploadFile(MultipartFile uploadFile, String bucketName, String objectName) {
        storageAdapter.uploadFile(uploadFile, bucketName, objectName);
        objectName = objectName + "/" + uploadFile.getOriginalFilename();
        return storageAdapter.getUrl(bucketName, objectName);
    }

}
