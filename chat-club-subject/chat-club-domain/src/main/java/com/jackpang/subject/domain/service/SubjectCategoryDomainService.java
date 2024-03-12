package com.jackpang.subject.domain.service;

import com.jackpang.subject.domain.entity.SubjectCategoryBO;

import java.util.List;

/**
 * description: SubjectCatagoryDomainService
 * date: 3/10/24 3:12 AM
 * author: jinhao_pang
 * version: 1.0
 */
public interface SubjectCategoryDomainService {
    /**
     * Add primary category
     */
    void add(SubjectCategoryBO SubjectCategoryBO);

    /**
     * Query primary category
     */
    List<SubjectCategoryBO> queryCategory(SubjectCategoryBO subjectCategoryBO);

    /**
     * update
     * @param subjectCategoryBO
     */
    Boolean update(SubjectCategoryBO subjectCategoryBO);

    Boolean delete(SubjectCategoryBO subjectCategoryBO);
}
