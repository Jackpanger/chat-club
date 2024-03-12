package com.jackpang.subject.application.convert;

import com.jackpang.subject.application.dto.SubjectCategoryDTO;
import com.jackpang.subject.domain.entity.SubjectCategoryBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectCategoryDTOConverter {
    SubjectCategoryDTOConverter INSTANCE = Mappers.getMapper(SubjectCategoryDTOConverter.class);
    SubjectCategoryBO convertDtoToBO(SubjectCategoryDTO subjectCategoryDTO);
    List<SubjectCategoryDTO> convertBOListToDtoList(List<SubjectCategoryBO> SubjectCategoryBOList);
}
