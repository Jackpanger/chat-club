package com.jackpang.oss.adapter;

import org.springframework.stereotype.Service;

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
}
