package com.jackpang.subject.domain.convert;

import com.jackpang.subject.domain.entity.SubjectAnswerBO;
import com.jackpang.subject.domain.entity.SubjectInfoBO;
import com.jackpang.subject.infra.basic.entity.SubjectBrief;
import com.jackpang.subject.infra.basic.entity.SubjectJudge;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectJudgeConverter {
    SubjectJudgeConverter INSTANCE = Mappers.getMapper(SubjectJudgeConverter.class);
    SubjectJudge convertBoToJudge(SubjectInfoBO subjectInfoBO);
    List<SubjectAnswerBO> convertBoJudgeToAnswerList(List<SubjectJudge> subjectJudges);
}
