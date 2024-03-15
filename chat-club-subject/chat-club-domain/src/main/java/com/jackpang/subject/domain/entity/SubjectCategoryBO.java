package com.jackpang.subject.domain.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 题目分类(SubjectCategory)实体类
 *
 * @author jackpang
 * @since 2024-03-09 22:20:50
 */
@Data
public class SubjectCategoryBO implements Serializable {
    private static final long serialVersionUID = -87455756503381905L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 分类名称
     */
    private String categoryName;
    /**
     * 分类类型
     */
    private Integer categoryType;
    /**
     * 图标连接
     */
    private String imageUrl;
    /**
     * 父级id
     */
    private Long parentId;

}

