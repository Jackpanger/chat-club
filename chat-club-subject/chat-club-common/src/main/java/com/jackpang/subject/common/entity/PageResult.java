package com.jackpang.subject.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * description: PageResult
 * date: 3/14/24 4:35 AM
 * author: jinhao_pang
 * version: 1.0
 */
@Data
public class PageResult<T> implements Serializable {
    private Integer pageNum = 1;
    private Integer pageSize = 20;
    private Integer total = 0;
    private Integer totalPages = 0;
    private List<T> result = Collections.emptyList();
    private Integer start = 1;
    private Integer end = 0;


    public void setRecords(List<T> result) {
        this.result = result;
        if (result != null && !result.isEmpty()) {
            setTotal(result.size());
        }
    }

    public void setTotal(Integer total) {
        this.total = total;
        if (pageSize > 0) {
            this.totalPages = total / this.pageSize + total % this.pageSize == 0 ? 0 : 1;
            this.start = (this.pageNum - 1) * this.pageSize + 1;
            this.end = this.start + this.pageSize - 1;
        } else {
            this.totalPages = 0;
            this.start = 1;
            this.end = 0;
        }

    }

}
