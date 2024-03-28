package com.jackpang.auth.application.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.jackpang.auth.application.convert.AuthPermissionDTOConverter;
import com.jackpang.auth.application.convert.AuthRoleDTOConverter;
import com.jackpang.auth.application.convert.AuthUserDTOConverter;
import com.jackpang.auth.application.dto.AuthPermissionDTO;
import com.jackpang.auth.application.dto.AuthRoleDTO;
import com.jackpang.auth.application.dto.AuthUserDTO;
import com.jackpang.auth.common.entity.Result;
import com.jackpang.auth.domain.entity.AuthPermissionBO;
import com.jackpang.auth.domain.entity.AuthRoleBO;
import com.jackpang.auth.domain.entity.AuthUserBO;
import com.jackpang.auth.domain.redis.RedisUtil;
import com.jackpang.auth.domain.service.AuthPermissionDomainService;
import com.jackpang.auth.domain.service.AuthRoleDomainService;
import com.jackpang.auth.infra.basic.entity.AuthPermission;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/permission/")
@Slf4j
public class PermissionController {
    @Resource
    private AuthPermissionDomainService authRoleDomainService;

    @RequestMapping("add")
    public Result<Boolean> add(@RequestBody AuthPermissionDTO authPermissionDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("PermissionController.add authPermission:{}", JSON.toJSONString(authPermissionDTO));
            }
            Preconditions.checkArgument(!StringUtils.isBlank(authPermissionDTO.getName()), "Permission name is null");
            Preconditions.checkNotNull(authPermissionDTO.getParentId(), "Parent id is null");

            AuthPermissionBO authPermissionBO = AuthPermissionDTOConverter.INSTANCE.convertDOtoToBO(authPermissionDTO);

            return Result.ok(authRoleDomainService.add(authPermissionBO));
        } catch (Exception e) {
            log.error("PermissionController.add error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }

    @RequestMapping("update")
    public Result<Boolean> update(@RequestBody AuthPermissionDTO authPermissionDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("PermissionController.update authPermission:{}", JSON.toJSONString(authPermissionDTO));
            }
            Preconditions.checkNotNull(authPermissionDTO.getId(), "ID is null");
            AuthPermissionBO authPermissionBO = AuthPermissionDTOConverter.INSTANCE.convertDOtoToBO(authPermissionDTO);
            return Result.ok(authRoleDomainService.update(authPermissionBO));
        } catch (Exception e) {
            log.error("PermissionController.update error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }

    @RequestMapping("changeStatus")
    public Result<Boolean> changeStatus(@RequestBody AuthPermissionDTO authPermissionDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("UserController.changeStatus authUserDTOh:{}", JSON.toJSONString(authPermissionDTO));
            }
            Preconditions.checkNotNull(authPermissionDTO.getId(), "ID is null");
            Preconditions.checkNotNull(authPermissionDTO.getStatus(), "status is null");
            AuthPermissionBO authPermissionBO = AuthPermissionDTOConverter.INSTANCE.convertDOtoToBO(authPermissionDTO);
            return Result.ok(authRoleDomainService.update(authPermissionBO));
        } catch (Exception e) {
            log.error("UserController.changeStatus error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }

    @RequestMapping("delete")
    public Result<Boolean> delete(@RequestBody AuthPermissionDTO authPermissionDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("PermissionController.delete authPermissionDTO:{}", JSON.toJSONString(authPermissionDTO));
            }
            AuthPermissionBO authPermissionBO = AuthPermissionDTOConverter.INSTANCE.convertDOtoToBO(authPermissionDTO);
            return Result.ok(authRoleDomainService.delete(authPermissionBO));
        } catch (Exception e) {
            log.error("PermissionController.delete error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }

    @RequestMapping("getPermission")
    public Result<Boolean> getPermission(@RequestParam("userName") String userName) {
        try {
            log.info("PermissionController.getPermission userName:{}", userName);
            Preconditions.checkArgument(!StringUtils.isBlank(userName), "userName is null");
            return Result.ok(authRoleDomainService.getPermission(userName));
        } catch (Exception e) {
            log.error("PermissionController.getPermission error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }

}
