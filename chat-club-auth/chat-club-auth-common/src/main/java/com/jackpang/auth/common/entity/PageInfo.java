package com.jackpang.auth.common.entity;

/**
 * description: PageRequest
 * date: 3/14/24 4:35 AM
 * author: jinhao_pang
 * version: 1.0
 */
public class PageInfo {
    private Integer pageNum = 1;
    private Integer pageSize = 20;

    public Integer getPageNum() {
        if (pageNum == null || pageNum < 1) {
            return 1;
        }
        return pageNum;
    }

    public Integer getPageSize() {
        if (pageSize == null || pageSize < 1 || pageSize > Integer.MAX_VALUE) {
            return 20;
        }
        return pageSize;
    }


}
