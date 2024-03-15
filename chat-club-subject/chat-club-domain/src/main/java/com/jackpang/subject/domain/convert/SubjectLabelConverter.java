package com.jackpang.subject.domain.convert;

import com.jackpang.subject.domain.entity.SubjectLabelBO;
import com.jackpang.subject.infra.basic.entity.SubjectLabel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectLabelConverter {
    SubjectLabelConverter INSTANCE = Mappers.getMapper(SubjectLabelConverter.class);
    SubjectLabel convertBoToLabel(SubjectLabelBO subjectLabelBO);

    List<SubjectLabelBO> convertLabelListToBoList(List<SubjectLabel> subjectLabelList);
}
