package com.jackpang.subject.domain.convert;

import com.jackpang.subject.domain.entity.SubjectAnswerBO;
import com.jackpang.subject.infra.basic.entity.SubjectMultiple;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectMultipleConverter {
    SubjectMultipleConverter INSTANCE = Mappers.getMapper(SubjectMultipleConverter.class);
    SubjectMultiple convertBoToMultiple(SubjectAnswerBO subjectAnswerBO);

    List<SubjectAnswerBO> convertBoMultipleToAnswerList(List<SubjectMultiple> result);
}
