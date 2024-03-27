package com.jackpang.auth.domain.convert;

import com.jackpang.auth.domain.entity.AuthUserBO;
import com.jackpang.auth.infra.basic.entity.AuthUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthUserBOConverter {
    AuthUserBOConverter INSTANCE = Mappers.getMapper(AuthUserBOConverter.class);
    AuthUser convertBOtoToUser(AuthUserBO authUserBO);

    AuthUserBO convertUserToBO(AuthUser authUser);
}
