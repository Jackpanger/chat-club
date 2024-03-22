package com.jackpang.oss.util;

import com.jackpang.oss.entity.FileInfo;
import io.minio.*;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;


import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * description: MinioUtil
 * date: 3/20/24 4:54 PM
 * author: jinhao_pang
 * version: 1.0
 */
@Component
public class MinioUtil {
    @Resource
    private MinioClient minioClient;

    public void createBucket(String bucketName) throws Exception {
        boolean exists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        if (!exists) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }
    }

    public void uploadFile(InputStream inputStream, String bucketName, String objectName) throws Exception {
        minioClient.putObject(PutObjectArgs.builder().bucket(bucketName).object(objectName)
                .stream(inputStream, -1, Integer.MAX_VALUE).build());
    }

    public List<String> getAllBucket() throws Exception {
        List<Bucket> buckets = minioClient.listBuckets();
        return buckets.stream().map(Bucket::name).collect(Collectors.toList());
    }

    public List<FileInfo> getAllFile(String bucketName) throws Exception {
        Iterable<Result<Item>> results = minioClient.listObjects(
                ListObjectsArgs.builder().bucket(bucketName).build());
        List<FileInfo> fileInfoList = new LinkedList<>();
        for (Result<Item> result : results) {
            FileInfo fileInfo = new FileInfo();
            Item item = result.get();
            fileInfo.setFileName(item.objectName());
            fileInfo.setEtag(item.etag());
            fileInfo.setDirectoryFlag(item.isDir());
            fileInfoList.add(fileInfo);
        }
        return fileInfoList;
    }

    public InputStream download(String bucketName, String objectName) throws Exception {
        return minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(objectName).build());
    }

    public void deleteBucket(String bucketName) throws Exception {
        minioClient.removeBucket(RemoveBucketArgs.builder().bucket(bucketName).build());
    }

    public void deleteFile(String bucketName, String objectName) throws Exception {
        minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(objectName).build());
    }
}
