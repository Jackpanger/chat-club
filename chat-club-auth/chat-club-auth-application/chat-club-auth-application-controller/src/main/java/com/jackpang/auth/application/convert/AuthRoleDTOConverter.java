package com.jackpang.auth.application.convert;

import com.jackpang.auth.application.dto.AuthRoleDTO;
import com.jackpang.auth.application.dto.AuthUserDTO;
import com.jackpang.auth.domain.entity.AuthRoleBO;
import com.jackpang.auth.domain.entity.AuthUserBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthRoleDTOConverter {
    AuthRoleDTOConverter INSTANCE = Mappers.getMapper(AuthRoleDTOConverter.class);
    AuthRoleBO convertDOtoToBO(AuthRoleDTO authRoleDTO);
}
