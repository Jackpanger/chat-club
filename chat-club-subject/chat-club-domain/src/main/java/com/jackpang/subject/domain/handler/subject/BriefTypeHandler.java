package com.jackpang.subject.domain.handler.subject;

import com.jackpang.subject.common.enums.IsDeletedFlagEnum;
import com.jackpang.subject.common.enums.SubjectInfoTypeEnum;
import com.jackpang.subject.domain.convert.SubjectBriefConverter;
import com.jackpang.subject.domain.entity.SubjectInfoBO;
import com.jackpang.subject.domain.entity.SubjectOptionBO;
import com.jackpang.subject.infra.basic.entity.SubjectBrief;
import com.jackpang.subject.infra.basic.service.SubjectBriefService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * description: BriefTypeHandler
 * date: 3/13/24 12:32 AM
 * author: jinhao_pang
 * version: 1.0
 */
@Component
public class BriefTypeHandler implements SubjectTypeHandler {
    @Resource
    private SubjectBriefService subjectBriefService;

    @Override
    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.BRIEF;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        // add brief subject
        SubjectBrief subjectBrief = SubjectBriefConverter.INSTANCE.convertBoToBrief(subjectInfoBO);
        subjectBrief.setSubjectId(subjectInfoBO.getId());
        subjectBrief.setIsDeleted(IsDeletedFlagEnum.NOT_DELETED.getCode());
        subjectBriefService.insert(subjectBrief);

    }

    @Override
    public SubjectOptionBO query(Long subjectId) {
        SubjectBrief subjectBrief = new SubjectBrief();
        subjectBrief.setSubjectId(subjectId);
        List<SubjectBrief> result = subjectBriefService.queryByCondition(subjectBrief);
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        if (result.size() == 1) {
            subjectOptionBO.setSubjectAnswer(result.get(0).getSubjectAnswer());
        }
        return subjectOptionBO;
    }
}
