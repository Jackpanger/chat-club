package com.jackpang.subject.application.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 题目标签表(SubjectLabel)实体类
 *
 * @author makejava
 * @since 2024-03-12 19:12:29
 */
@Data
public class SubjectLabelDTO implements Serializable {
    private static final long serialVersionUID = 882689328768509252L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 分类id
     */
    private Long categoryId;
    /**
     * 标签分类
     */
    private String labelName;
    /**
     * 排序
     */
    private Integer sortNum;


}

