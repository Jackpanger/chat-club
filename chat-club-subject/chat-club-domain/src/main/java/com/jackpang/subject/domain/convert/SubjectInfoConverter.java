package com.jackpang.subject.domain.convert;

import com.jackpang.subject.domain.entity.SubjectInfoBO;
import com.jackpang.subject.domain.entity.SubjectOptionBO;
import com.jackpang.subject.infra.basic.entity.SubjectInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectInfoConverter {
    SubjectInfoConverter INSTANCE = Mappers.getMapper(SubjectInfoConverter.class);
    SubjectInfo convertBoToInfo(SubjectInfoBO subjectInfoBO);
    SubjectInfoBO convertOptionToInfo(SubjectOptionBO subjectOptionBO);
    SubjectInfoBO concatOptionAndInfo(SubjectOptionBO subjectOptionBO, SubjectInfo subjectInfo);
    List<SubjectInfoBO> convertBoToInfoList(List<SubjectInfo> subjectInfoList);
}
