package com.jackpang.auth.domain.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.google.gson.Gson;
import com.jackpang.auth.common.enums.AuthUserStatusEnum;
import com.jackpang.auth.common.enums.IsDeletedFlagEnum;
import com.jackpang.auth.domain.constants.AuthConstant;
import com.jackpang.auth.domain.convert.AuthUserBOConverter;
import com.jackpang.auth.domain.entity.AuthUserBO;
import com.jackpang.auth.domain.redis.RedisUtil;
import com.jackpang.auth.domain.service.AuthUserDomainService;
import com.jackpang.auth.infra.basic.entity.*;
import com.jackpang.auth.infra.basic.service.*;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
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
public class AuthUserDomainServiceImpl implements AuthUserDomainService {
    @Resource
    private AuthUserService authUserService;
    @Resource
    private AuthUserRoleService authUserRoleService;
    @Resource
    private AuthRoleService authRoleService;
    @Resource
    private AuthPermissionService authPermissionService;
    @Resource
    private AuthRolePermissionService authRolePermissionService;

    @Resource
    private RedisUtil redisUtil;
    private String authPermissionPrefix = "auth.permission";
    private String authRolePrefix = "auth.role";
    private static final String LOGIN_PREFIX = "loginCode";

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean register(AuthUserBO authUserBO) {
        if (log.isInfoEnabled()) {
            log.info("AuthUserDomainServiceImpl.register authUserBO:{}", JSON.toJSONString(authUserBO));
        }
        AuthUser existAuthUser = new AuthUser();
        existAuthUser.setUserName(authUserBO.getUserName());
        // check if the user exists
        List<AuthUser> authUsers = authUserService.queryByCondition(existAuthUser);
        if (!authUsers.isEmpty()) {
            return true;
        }

        AuthUser authUser = AuthUserBOConverter.INSTANCE.convertBOtoToUser(authUserBO);

        if (StringUtils.isNotBlank(authUser.getPassword()))
            authUser.setPassword(SaSecureUtil.md5BySalt(authUser.getPassword(), AuthConstant.SALT));
        authUser.setStatus(AuthUserStatusEnum.OPEN.getCode());
        authUser.setIsDeleted(IsDeletedFlagEnum.NOT_DELETED.getCode());
        Integer count = authUserService.insert(authUser);
        // set a role
        AuthRole authRole = new AuthRole();
        authRole.setRoleKey(AuthConstant.NORMAL_USER);
        AuthRole roleResult = authRoleService.queryByCondition(authRole);
        Long roleId = roleResult.getId();
        Long userId = authUser.getId();
        AuthUserRole authUserRole = new AuthUserRole();
        authUserRole.setRoleId(roleId);
        authUserRole.setUserId(userId);
        authUserRole.setIsDeleted(IsDeletedFlagEnum.NOT_DELETED.getCode());
        authUserRoleService.insert(authUserRole);
        // put it in the redis
        String roleKey = redisUtil.buildKey(authRolePrefix, authUser.getUserName());
        List<AuthRole> roleList = new LinkedList<>();
        roleList.add(roleResult);
        redisUtil.set(roleKey, new Gson().toJson(roleList));
        AuthRolePermission authRolePermission = new AuthRolePermission();
        authRolePermission.setRoleId(roleId);
        List<AuthRolePermission> rolePermissionList = authRolePermissionService.queryByCondition(authRolePermission);

        List<Long> permessionIdList = rolePermissionList.stream().map(AuthRolePermission::getPermissionId).collect(Collectors.toList());
        List<AuthPermission> authPermissionList = authPermissionService.queryByPermissionList(permessionIdList);
        String permissionKey = redisUtil.buildKey(authPermissionPrefix, authUser.getUserName());
        redisUtil.set(permissionKey, new Gson().toJson(authPermissionList));


        return count > 0;
    }

    @Override
    public Boolean update(AuthUserBO authUserBO) {
        if (log.isInfoEnabled()) {
            log.info("AuthUserDomainServiceImpl.update authUserBO:{}", JSON.toJSONString(authUserBO));
        }
        AuthUser authUser = AuthUserBOConverter.INSTANCE.convertBOtoToUser(authUserBO);
        Integer count = authUserService.update(authUser);
        // put it in the redis
        return count > 0;
    }

    @Override
    public Boolean delete(AuthUserBO authUserBO) {
        if (log.isInfoEnabled()) {
            log.info("AuthUserDomainServiceImpl.delete authUserBO:{}", JSON.toJSONString(authUserBO));
        }

        AuthUser authUser = new AuthUser();
        authUser.setId(authUserBO.getId());
        authUser.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        Integer count = authUserService.update(authUser);
        // put it in the redis
        return count > 0;
    }

    @Override
    public SaTokenInfo doLogin(String validCode) {
        String loginKey = redisUtil.buildKey(LOGIN_PREFIX, validCode);
        String openId = redisUtil.get(loginKey);
        if (StringUtils.isBlank(openId)) {
            return null;
        }
        AuthUserBO authUserBO = new AuthUserBO();
        authUserBO.setUserName(openId);
        this.register(authUserBO);
        StpUtil.login(openId);
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        return tokenInfo;
    }

    @Override
    public AuthUserBO getUserInfo(AuthUserBO authUserBO) {
        AuthUser authUser = new AuthUser();
        authUser.setUserName(authUserBO.getUserName());
        List<AuthUser> authUsers = authUserService.queryByCondition(authUser);
        if (CollectionUtils.isEmpty(authUsers)) {
            return new AuthUserBO();
        }
        return AuthUserBOConverter.INSTANCE.convertUserToBO(authUsers.get(0));
    }

}
