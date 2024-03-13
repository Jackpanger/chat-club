package com.jackpang.subject.infra.basic.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 判断题(SubjectJudge)实体类
 *
 * @author makejava
 * @since 2024-03-12 23:17:10
 */
@Data
public class SubjectJudge implements Serializable {
    private static final long serialVersionUID = -25042814122069883L;
/**
     * 主键
     */
    private Long id;
/**
     * 题目id
     */
    private Long subjectId;
/**
     * 是否正确
     */
    private Integer isCorrect;
/**
     * 创建人
     */
    private String createdBy;
/**
     * 创建时间
     */
    private Date createdTime;
/**
     * 更新人
     */
    private String updateBy;
/**
     * 更新时间
     */
    private Date updateTime;

    private Integer isDeleted;


}

