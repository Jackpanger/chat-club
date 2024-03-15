package com.jackpang.subject.domain.handler.subject;

import com.jackpang.subject.common.enums.SubjectInfoTypeEnum;
import com.jackpang.subject.domain.entity.SubjectInfoBO;
import com.jackpang.subject.domain.entity.SubjectOptionBO;

public interface SubjectTypeHandler {
    /**
     * Get handler type
     */
    SubjectInfoTypeEnum getHandlerType();
    /**
     * Add subject
     */
    void add(SubjectInfoBO subjectInfoBO);
    SubjectOptionBO query(Long subjectId);
}

