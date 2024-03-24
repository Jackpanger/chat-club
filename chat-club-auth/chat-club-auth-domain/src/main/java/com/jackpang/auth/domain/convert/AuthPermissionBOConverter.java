package com.jackpang.auth.domain.convert;

import com.jackpang.auth.domain.entity.AuthPermissionBO;
import com.jackpang.auth.infra.basic.entity.AuthPermission;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthPermissionBOConverter {
    AuthPermissionBOConverter INSTANCE = Mappers.getMapper(AuthPermissionBOConverter.class);
    AuthPermission convertBOtoToUser(AuthPermissionBO authPermissionBO);
}
