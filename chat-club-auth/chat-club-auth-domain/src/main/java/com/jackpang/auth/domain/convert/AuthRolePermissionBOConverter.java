package com.jackpang.auth.domain.convert;

import com.jackpang.auth.domain.entity.AuthPermissionBO;
import com.jackpang.auth.domain.entity.AuthRolePermissionBO;
import com.jackpang.auth.infra.basic.entity.AuthPermission;
import com.jackpang.auth.infra.basic.entity.AuthRolePermission;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthRolePermissionBOConverter {
    AuthRolePermissionBOConverter INSTANCE = Mappers.getMapper(AuthRolePermissionBOConverter.class);
    AuthRolePermission convertBOtoToUser(AuthRolePermissionBO authRolePermissionBO);
}
