package com.jackpang.subject.domain.convert;

import com.jackpang.subject.domain.entity.SubjectAnswerBO;
import com.jackpang.subject.infra.basic.entity.SubjectRadio;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectRadioConverter {
    SubjectRadioConverter INSTANCE = Mappers.getMapper(SubjectRadioConverter.class);
    SubjectRadio convertBoToRadio(SubjectAnswerBO subjectAnswerBO);

    List<SubjectAnswerBO> convertBoRadioToAnswerList(List<SubjectRadio> result);
}
