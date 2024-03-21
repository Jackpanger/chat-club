package com.jackpang.subject.domain.handler.subject;

import com.jackpang.subject.common.enums.IsDeletedFlagEnum;
import com.jackpang.subject.common.enums.SubjectInfoTypeEnum;
import com.jackpang.subject.domain.convert.SubjectMultipleConverter;
import com.jackpang.subject.domain.entity.SubjectAnswerBO;
import com.jackpang.subject.domain.entity.SubjectInfoBO;
import com.jackpang.subject.domain.entity.SubjectOptionBO;
import com.jackpang.subject.infra.basic.entity.SubjectMultiple;
import com.jackpang.subject.infra.basic.service.SubjectMultipleService;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * description: MultipleTypeHandler
 * date: 3/13/24 12:32 AM
 * author: jinhao_pang
 * version: 1.0
 */
@Component
public class MultipleTypeHandler implements SubjectTypeHandler{
    @Resource
    SubjectMultipleService subjectMultipleService;
    @Override
    public SubjectInfoTypeEnum getHandlerType() {
         return SubjectInfoTypeEnum.MULTIPLE;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        // add multiple subject
        List<SubjectMultiple> subjectMultipleList = new LinkedList<>();
        subjectInfoBO.getOptionList().forEach(option -> {
            SubjectMultiple subjectMultiple = SubjectMultipleConverter.INSTANCE.convertBoToMultiple(option);
            subjectMultiple.setSubjectId(subjectInfoBO.getId());
            subjectMultiple.setIsDeleted(IsDeletedFlagEnum.NOT_DELETED.getCode());
            subjectMultipleList.add(subjectMultiple);
        });
        subjectMultipleService.insertBatch(subjectMultipleList);
    }

    @Override
    public SubjectOptionBO query(Long subjectId) {
        SubjectMultiple subjectMultiple = new SubjectMultiple();
        subjectMultiple.setSubjectId(subjectId);
        List<SubjectMultiple> result = subjectMultipleService.queryByCondition(subjectMultiple);
        List<SubjectAnswerBO> subjectAnswerBOS = SubjectMultipleConverter.INSTANCE.convertBoMultipleToAnswerList(result);
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        subjectOptionBO.setOptionList(subjectAnswerBOS);
        return subjectOptionBO;
    }
}
