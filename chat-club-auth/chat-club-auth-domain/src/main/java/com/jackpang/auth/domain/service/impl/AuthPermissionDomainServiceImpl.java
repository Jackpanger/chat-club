package com.jackpang.auth.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jackpang.auth.common.enums.IsDeletedFlagEnum;
import com.jackpang.auth.domain.convert.AuthPermissionBOConverter;
import com.jackpang.auth.domain.convert.AuthRoleBOConverter;
import com.jackpang.auth.domain.entity.AuthPermissionBO;
import com.jackpang.auth.domain.entity.AuthRoleBO;
import com.jackpang.auth.domain.redis.RedisUtil;
import com.jackpang.auth.domain.service.AuthPermissionDomainService;
import com.jackpang.auth.domain.service.AuthRoleDomainService;
import com.jackpang.auth.infra.basic.entity.AuthPermission;
import com.jackpang.auth.infra.basic.entity.AuthRole;
import com.jackpang.auth.infra.basic.service.AuthPermissionService;
import com.jackpang.auth.infra.basic.service.AuthRoleService;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


/**
 * description: SubjectCategoryDomainServiceImpl
 * date: 3/10/24 3:13 AM
 * author: jinhao_pang
 * version: 1.0
 */
@Service
@Slf4j
public class AuthPermissionDomainServiceImpl implements AuthPermissionDomainService {
    @Resource
    private AuthPermissionService authPermissionService;
    @Resource
    private RedisUtil redisUtil;
    private String authPermissionPrefix = "auth.permission";

    @Override
    public Boolean add(AuthPermissionBO authPermissionBO) {
        if (log.isInfoEnabled()) {
            log.info("AuthPermissionDomainServiceImpl.add authPermissionBO:{}", JSON.toJSONString(authPermissionBO));
        }
        AuthPermission authPermission = AuthPermissionBOConverter.INSTANCE.convertBOtoToUser(authPermissionBO);
        authPermission.setIsDeleted(IsDeletedFlagEnum.NOT_DELETED.getCode());
        Integer count = authPermissionService.insert(authPermission);
        return count > 0;
    }
    @Override
    public Boolean update(AuthPermissionBO authPermissionBO) {
        if (log.isInfoEnabled()) {
            log.info("AuthPermissionDomainServiceImpl.update authPermissionBO:{}", JSON.toJSONString(authPermissionBO));
        }
        AuthPermission authPermission = AuthPermissionBOConverter.INSTANCE.convertBOtoToUser(authPermissionBO);
        Integer count = authPermissionService.update(authPermission);
        // put it in the redis
        return count > 0;
    }

    @Override
    public Boolean delete(AuthPermissionBO authPermissionBO) {
        if (log.isInfoEnabled()) {
            log.info("AuthPermissionDomainServiceImpl.delete authPermissionBO:{}", JSON.toJSONString(authPermissionBO));
        }

        AuthPermission authPermission = new AuthPermission();
        authPermission.setId(authPermissionBO.getId());
        authPermission.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        Integer count = authPermissionService.update(authPermission);
        // put it in the redis
        return count > 0;
    }

    @Override
    public List<String> getPermission(String userName) {

        String permissionKey = redisUtil.buildKey(authPermissionPrefix, userName);
        String permissionValue = redisUtil.get(permissionKey);
        if (StringUtils.isBlank(permissionValue)){
            return Collections.emptyList();
        }
        List<AuthPermission> permissionList = new Gson().fromJson(permissionValue, new TypeToken<List<AuthPermission>>() {
        }.getType());
        List<String> authList = permissionList.stream().map(AuthPermission::getPermissionKey).collect(Collectors.toList());
        return authList;
    }

}
