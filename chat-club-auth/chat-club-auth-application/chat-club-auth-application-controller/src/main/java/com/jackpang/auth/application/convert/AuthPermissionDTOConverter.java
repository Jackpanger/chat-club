package com.jackpang.auth.application.convert;

import com.jackpang.auth.application.dto.AuthPermissionDTO;
import com.jackpang.auth.domain.entity.AuthPermissionBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthPermissionDTOConverter {
    AuthPermissionDTOConverter INSTANCE = Mappers.getMapper(AuthPermissionDTOConverter.class);
    AuthPermissionBO convertDOtoToBO(AuthPermissionDTO AuthPermissionDTO);
}
