package com.jackpang.auth.application.convert;

import com.jackpang.auth.application.dto.AuthUserDTO;
import com.jackpang.auth.domain.entity.AuthUserBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthUserDTOConverter {
    AuthUserDTOConverter INSTANCE = Mappers.getMapper(AuthUserDTOConverter.class);
    AuthUserBO convertDOtoToBO(AuthUserDTO authUserDTO);
}
