package com.jackpang.subject.application.convert;

import com.jackpang.subject.application.dto.SubjectLabelDTO;
import com.jackpang.subject.domain.entity.SubjectLabelBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectLabelDTOConverter {
    SubjectLabelDTOConverter INSTANCE = Mappers.getMapper(SubjectLabelDTOConverter.class);
    SubjectLabelBO convertDOtoToBO(SubjectLabelDTO subjectLabelDTO);
    List<SubjectLabelDTO> convertBOListToDTOList(List<SubjectLabelBO> subjectLabelBOList);
}
