package com.jackpang.auth.application.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.jackpang.auth.application.convert.AuthRoleDTOConverter;
import com.jackpang.auth.application.dto.AuthRoleDTO;
import com.jackpang.auth.common.entity.Result;
import com.jackpang.auth.domain.entity.AuthRoleBO;
import com.jackpang.auth.domain.service.AuthRoleDomainService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/permission/")
@Slf4j
public class PermissionController {
    @Resource
    private AuthRoleDomainService authRoleDomainService;

    @RequestMapping("add")
    public Result<Boolean> add(@RequestBody AuthRoleDTO authRoleDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("RoleController.add authRoleDTO:{}", JSON.toJSONString(authRoleDTO));
            }
            Preconditions.checkArgument(!StringUtils.isBlank(authRoleDTO.getRoleName()), "Role name is null");
            Preconditions.checkArgument(!StringUtils.isBlank(authRoleDTO.getRoleKey()), "Role key is null");

            AuthRoleBO authRoleBO = AuthRoleDTOConverter.INSTANCE.convertDOtoToBO(authRoleDTO);

            return Result.ok(authRoleDomainService.add(authRoleBO));
        } catch (Exception e) {
            log.error("RoleController.add error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }

    @RequestMapping("update")
    public Result<Boolean> update(@RequestBody AuthRoleDTO authRoleDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("RoleController.update authRoleDTO:{}", JSON.toJSONString(authRoleDTO));
            }
            Preconditions.checkNotNull(authRoleDTO.getId(), "ID is null");
            AuthRoleBO authRoleBO = AuthRoleDTOConverter.INSTANCE.convertDOtoToBO(authRoleDTO);
            return Result.ok(authRoleDomainService.update(authRoleBO));
        } catch (Exception e) {
            log.error("RoleController.update error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }


    @RequestMapping("delete")
    public Result<Boolean> delete(@RequestBody AuthRoleDTO authRoleDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("RoleController.delete authRoleDTO:{}", JSON.toJSONString(authRoleDTO));
            }
            AuthRoleBO authRoleBO = AuthRoleDTOConverter.INSTANCE.convertDOtoToBO(authRoleDTO);
            return Result.ok(authRoleDomainService.delete(authRoleBO));
        } catch (Exception e) {
            log.error("RoleController.delete error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }

}
