package com.jackpang.oss.adapter;

import com.jackpang.oss.entity.FileInfo;
import com.jackpang.oss.util.MinioUtil;
import javax.annotation.Resource;
import lombok.SneakyThrows;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * description: MinioStorageServiceImpl
 * date: 3/20/24 5:25 PM
 * author: jinhao_pang
 * version: 1.0
 */
public class MinioStorageAdapter implements StorageAdapter {
    @Resource
    private MinioUtil minioUtil;

    @Override
    @SneakyThrows
    public void createBucket(String bucketName) {
        minioUtil.createBucket(bucketName);
    }

    @Override
    @SneakyThrows
    public void uploadFile(MultipartFile uploadFile, String bucketName, String objectName) {
        minioUtil.createBucket(bucketName);
        if (objectName != null) {
            minioUtil.uploadFile(uploadFile.getInputStream(), bucketName, objectName + "/" + uploadFile.getName());
        } else {
            minioUtil.uploadFile(uploadFile.getInputStream(), bucketName, uploadFile.getName());
        }
    }

    @Override
    @SneakyThrows
    public List<String> getAllBucket() {
        return minioUtil.getAllBucket();
    }

    @Override
    @SneakyThrows
    public List<FileInfo> getAllFile(String bucketName) {
        return minioUtil.getAllFile(bucketName);
    }

    @Override
    @SneakyThrows
    public InputStream download(String bucketName, String objectName) {
        return minioUtil.download(bucketName, objectName);
    }

    @Override
    @SneakyThrows
    public void deleteBucket(String bucketName) {
        minioUtil.deleteBucket(bucketName);
    }

    @Override
    @SneakyThrows
    public void deleteFile(String bucketName, String objectName) {
        minioUtil.deleteFile(bucketName,objectName);
    }
}
