package com.jackpang.subject.application.convert;

import com.jackpang.subject.application.dto.SubjectInfoDTO;
import com.jackpang.subject.domain.entity.SubjectInfoBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubjectInfoDTOConverter {
    SubjectInfoDTOConverter INSTANCE = Mappers.getMapper(SubjectInfoDTOConverter.class);
    SubjectInfoBO convertDOtoToBO(SubjectInfoDTO subjectInfoDTO);

    SubjectInfoDTO convertBOtoToDTO(SubjectInfoBO subjectInfoBO);
}
