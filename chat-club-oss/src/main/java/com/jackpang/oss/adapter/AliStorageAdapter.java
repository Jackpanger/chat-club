package com.jackpang.oss.adapter;

import com.jackpang.oss.entity.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * description: AliStorageServiceImpl
 * date: 3/20/24 6:11 PM
 * author: jinhao_pang
 * version: 1.0
 */
public class AliStorageAdapter implements StorageAdapter {
    @Override
    public void createBucket(String bucketName) {

    }

    @Override
    public void uploadFile(MultipartFile uploadFile, String bucketName, String objectName) {

    }

    @Override
    public List<String> getAllBucket() {
        return null;
    }

    @Override
    public List<FileInfo> getAllFile(String bucketName) {
        return null;
    }

    @Override
    public InputStream download(String bucketName, String objectName) {
        return null;
    }

    @Override
    public void deleteBucket(String bucketName) {

    }

    @Override
    public void deleteFile(String bucketName, String objectName) {

    }
}
