package com.jackpang.auth.domain.convert;

import com.jackpang.auth.domain.entity.AuthRoleBO;
import com.jackpang.auth.domain.entity.AuthUserBO;
import com.jackpang.auth.infra.basic.entity.AuthRole;
import com.jackpang.auth.infra.basic.entity.AuthUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthRoleBOConverter {
    AuthRoleBOConverter INSTANCE = Mappers.getMapper(AuthRoleBOConverter.class);
    AuthRole convertBOtoToUser(AuthRoleBO authRoleBO);
}
