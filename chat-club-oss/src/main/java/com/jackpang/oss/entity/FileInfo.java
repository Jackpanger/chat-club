package com.jackpang.oss.entity;

import lombok.Data;

/**
 * description: FileInfo
 * date: 3/20/24 5:05 PM
 * author: jinhao_pang
 * version: 1.0
 */
@Data
public class FileInfo {
    private String fileName;
    private Boolean directoryFlag;
    private String etag;
}
