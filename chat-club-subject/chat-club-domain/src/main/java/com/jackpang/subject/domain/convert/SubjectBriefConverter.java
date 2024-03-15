package com.jackpang.subject.domain.convert;

import com.jackpang.subject.domain.entity.SubjectInfoBO;
import com.jackpang.subject.infra.basic.entity.SubjectBrief;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubjectBriefConverter {
    SubjectBriefConverter INSTANCE = Mappers.getMapper(SubjectBriefConverter.class);
    SubjectBrief convertBoToBrief(SubjectInfoBO subjectInfoBO);
}
