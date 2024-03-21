package com.jackpang.subject.domain.handler.subject;

import com.jackpang.subject.common.enums.IsDeletedFlagEnum;
import com.jackpang.subject.common.enums.SubjectInfoTypeEnum;
import com.jackpang.subject.domain.convert.SubjectJudgeConverter;
import com.jackpang.subject.domain.entity.SubjectAnswerBO;
import com.jackpang.subject.domain.entity.SubjectInfoBO;
import com.jackpang.subject.domain.entity.SubjectOptionBO;
import com.jackpang.subject.infra.basic.entity.SubjectJudge;
import com.jackpang.subject.infra.basic.service.SubjectJudgeService;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * description: JudgeTypeHandler
 * date: 3/13/24 12:32 AM
 * author: jinhao_pang
 * version: 1.0
 */
@Component
public class JudgeTypeHandler implements SubjectTypeHandler{
    @Resource
    SubjectJudgeService subjectJudgeService;
    @Override
    public SubjectInfoTypeEnum getHandlerType() {
         return SubjectInfoTypeEnum.JUDGE;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        // add judge subject
        SubjectJudge subjectJudge = new SubjectJudge();
        SubjectAnswerBO subjectAnswerBO = subjectInfoBO.getOptionList().get(0);
        subjectJudge.setSubjectId(subjectInfoBO.getId());
        subjectJudge.setIsDeleted(IsDeletedFlagEnum.NOT_DELETED.getCode());
        subjectJudge.setIsCorrect(subjectAnswerBO.getIsCorrect());
        subjectJudgeService.insert(subjectJudge);

    }

    @Override
    public SubjectOptionBO query(Long subjectId) {
        SubjectJudge subjectJudge = new SubjectJudge();
        subjectJudge.setSubjectId(subjectId);
        List<SubjectJudge> result = subjectJudgeService.queryByCondition(subjectJudge);
        List<SubjectAnswerBO> subjectAnswerBOS = SubjectJudgeConverter.INSTANCE.convertBoJudgeToAnswerList(result);
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        subjectOptionBO.setOptionList(subjectAnswerBOS);
        return subjectOptionBO;
    }
}
