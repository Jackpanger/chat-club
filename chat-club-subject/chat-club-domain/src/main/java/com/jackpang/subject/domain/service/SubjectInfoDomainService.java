package com.jackpang.subject.domain.service;

import com.jackpang.subject.common.entity.PageResult;
import com.jackpang.subject.domain.entity.SubjectInfoBO;

/**
 * description: SubjectInfoDomainService
 * date: 3/10/24 3:12 AM
 * author: jinhao_pang
 * version: 1.0
 */
public interface SubjectInfoDomainService {
    /**
     * Add subject
     */
    void add(SubjectInfoBO subjectInfoBO);

    PageResult<SubjectInfoBO> getSubjectPage(SubjectInfoBO subjectInfoBO);

    SubjectInfoBO querySubjectInfo(SubjectInfoBO subjectInfoBO);
}
