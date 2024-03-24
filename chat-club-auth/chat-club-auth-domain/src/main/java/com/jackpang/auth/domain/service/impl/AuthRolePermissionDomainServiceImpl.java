package com.jackpang.auth.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.jackpang.auth.common.enums.IsDeletedFlagEnum;
import com.jackpang.auth.domain.convert.AuthPermissionBOConverter;
import com.jackpang.auth.domain.convert.AuthRolePermissionBOConverter;
import com.jackpang.auth.domain.entity.AuthRolePermissionBO;
import com.jackpang.auth.domain.service.AuthRolePermissionDomainService;
import com.jackpang.auth.infra.basic.entity.AuthPermission;
import com.jackpang.auth.infra.basic.entity.AuthRolePermission;
import com.jackpang.auth.infra.basic.service.AuthRolePermissionService;
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
public class AuthRolePermissionDomainServiceImpl implements AuthRolePermissionDomainService {
    @Resource
    private AuthRolePermissionService authRolePermissionService;

    @Override
    public Boolean add(AuthRolePermissionBO authRolePermissionBO) {
        if (log.isInfoEnabled()) {
            log.info("AuthRolePermissionDomainServiceImpl.add authRolePermissionBO:{}", JSON.toJSONString(authRolePermissionBO));
        }
        authRolePermissionBO.getPermissionIds().forEach(permissionId -> {
            AuthRolePermission authRolePermission = new AuthRolePermission();
            authRolePermission.setRoleId(authRolePermissionBO.getRoleId());
            authRolePermission.setPermissionId(permissionId);
            authRolePermission.setIsDeleted(IsDeletedFlagEnum.NOT_DELETED.getCode());
            authRolePermissionService.insert(authRolePermission);
        });
        AuthRolePermission authRolePermission = AuthRolePermissionBOConverter.INSTANCE.convertBOtoToUser(authRolePermissionBO);
        authRolePermission.setIsDeleted(IsDeletedFlagEnum.NOT_DELETED.getCode());
        Integer count = authRolePermissionService.insert(authRolePermission);
        return count > 0;
    }
//    @Override
//    public Boolean update(AuthPermissionBO authPermissionBO) {
//        if (log.isInfoEnabled()) {
//            log.info("AuthPermissionDomainServiceImpl.update authPermissionBO:{}", JSON.toJSONString(authPermissionBO));
//        }
//        AuthPermission authPermission = AuthPermissionBOConverter.INSTANCE.convertBOtoToUser(authPermissionBO);
//        Integer count = authPermissionService.update(authPermission);
//        // put it in the redis
//        return count > 0;
//    }
//
//    @Override
//    public Boolean delete(AuthPermissionBO authPermissionBO) {
//        if (log.isInfoEnabled()) {
//            log.info("AuthPermissionDomainServiceImpl.delete authPermissionBO:{}", JSON.toJSONString(authPermissionBO));
//        }
//
//        AuthPermission authPermission = new AuthPermission();
//        authPermission.setId(authPermissionBO.getId());
//        authPermission.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
//        Integer count = authPermissionService.update(authPermission);
//        // put it in the redis
//        return count > 0;
//    }

}
