package com.jackpang.auth.domain.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import com.alibaba.fastjson.JSON;
import com.jackpang.auth.common.enums.AuthUserStatusEnum;
import com.jackpang.auth.common.enums.IsDeletedFlagEnum;
import com.jackpang.auth.domain.convert.AuthRoleBOConverter;
import com.jackpang.auth.domain.convert.AuthUserBOConverter;
import com.jackpang.auth.domain.entity.AuthRoleBO;
import com.jackpang.auth.domain.entity.AuthUserBO;
import com.jackpang.auth.domain.service.AuthRoleDomainService;
import com.jackpang.auth.domain.service.AuthUserDomainService;
import com.jackpang.auth.infra.basic.entity.AuthRole;
import com.jackpang.auth.infra.basic.entity.AuthUser;
import com.jackpang.auth.infra.basic.service.AuthRoleService;
import com.jackpang.auth.infra.basic.service.AuthUserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * description: SubjectCategoryDomainServiceImpl
 * date: 3/10/24 3:13 AM
 * author: jinhao_pang
 * version: 1.0
 */
@Service
@Slf4j
public class AuthRoleDomainServiceImpl implements AuthRoleDomainService {
    @Resource
    private AuthRoleService authRoleService;
    private String salt = "jack";

    @Override
    public Boolean add(AuthRoleBO authRoleBO) {
        if (log.isInfoEnabled()) {
            log.info("AuthRoleDomainServiceImpl.add authRoleBO:{}", JSON.toJSONString(authRoleBO));
        }
        AuthRole authRole = AuthRoleBOConverter.INSTANCE.convertBOtoToUser(authRoleBO);
        authRole.setIsDeleted(IsDeletedFlagEnum.NOT_DELETED.getCode());
        Integer count = authRoleService.insert(authRole);
        return count > 0;
    }
    @Override
    public Boolean update(AuthRoleBO authRoleBO) {
        if (log.isInfoEnabled()) {
            log.info("AuthRoleDomainServiceImpl.update authRoleBO:{}", JSON.toJSONString(authRoleBO));
        }
        AuthRole authRole = AuthRoleBOConverter.INSTANCE.convertBOtoToUser(authRoleBO);
        Integer count = authRoleService.update(authRole);
        // put it in the redis
        return count > 0;
    }

    @Override
    public Boolean delete(AuthRoleBO authRoleBO) {
        if (log.isInfoEnabled()) {
            log.info("AuthRoleDomainServiceImpl.delete authRoleBO:{}", JSON.toJSONString(authRoleBO));
        }

        AuthRole AuthRole = new AuthRole();
        AuthRole.setId(authRoleBO.getId());
        AuthRole.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        Integer count = authRoleService.update(AuthRole);
        // put it in the redis
        return count > 0;
    }

}
