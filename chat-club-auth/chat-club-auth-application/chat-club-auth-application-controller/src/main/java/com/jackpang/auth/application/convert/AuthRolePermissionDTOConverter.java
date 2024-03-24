package com.jackpang.auth.application.convert;

import com.jackpang.auth.application.dto.AuthPermissionDTO;
import com.jackpang.auth.application.dto.AuthRolePermissionDTO;
import com.jackpang.auth.domain.entity.AuthPermissionBO;
import com.jackpang.auth.domain.entity.AuthRolePermissionBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthRolePermissionDTOConverter {
    AuthRolePermissionDTOConverter INSTANCE = Mappers.getMapper(AuthRolePermissionDTOConverter.class);
    AuthRolePermissionBO convertDOtoToBO(AuthRolePermissionDTO authRolePermissionDTO);
}
