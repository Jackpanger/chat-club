package com.jackpang.subject.domain.service;

import com.jackpang.subject.domain.entity.SubjectLabelBO;

import java.util.List;

/**
 * description: SubjectLabelDomainService
 * date: 3/10/24 3:12 AM
 * author: jinhao_pang
 * version: 1.0
 */
public interface SubjectLabelDomainService {
    /**
     * Add label
     */
    Boolean add(SubjectLabelBO subjectLabelBO);
    /**
     * update label
     */
    Boolean update(SubjectLabelBO subjectLabelBO);
    Boolean delete(SubjectLabelBO subjectLabelBO);

    List<SubjectLabelBO> queryLabelByCategoryId(SubjectLabelBO subjectLabelBO);
}
