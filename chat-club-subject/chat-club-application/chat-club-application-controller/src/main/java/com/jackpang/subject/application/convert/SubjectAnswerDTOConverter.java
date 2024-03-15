package com.jackpang.subject.application.convert;

import com.jackpang.subject.application.dto.SubjectAnswerDTO;
import com.jackpang.subject.domain.entity.SubjectAnswerBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectAnswerDTOConverter {
    SubjectAnswerDTOConverter INSTANCE = Mappers.getMapper(SubjectAnswerDTOConverter.class);
    SubjectAnswerBO convertDTOtoToBO(SubjectAnswerDTO subjectAnswerDTO);
    List<SubjectAnswerBO> convertDTOListToBO(List<SubjectAnswerDTO> subjectAnswerDTOs);

}
