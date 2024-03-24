package com.jackpang.auth.application.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.google.common.base.Preconditions;
import com.jackpang.auth.application.convert.AuthPermissionDTOConverter;
import com.jackpang.auth.application.convert.AuthRolePermissionDTOConverter;
import com.jackpang.auth.application.dto.AuthPermissionDTO;
import com.jackpang.auth.application.dto.AuthRolePermissionDTO;
import com.jackpang.auth.common.entity.Result;
import com.jackpang.auth.domain.entity.AuthPermissionBO;
import com.jackpang.auth.domain.entity.AuthRolePermissionBO;
import com.jackpang.auth.domain.service.AuthPermissionDomainService;
import com.jackpang.auth.domain.service.AuthRolePermissionDomainService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rolePermission/")
@Slf4j
public class RolePermissionController {
    @Resource
    private AuthRolePermissionDomainService authRolePermissionDomainService;

    @RequestMapping("add")
    public Result<Boolean> add(@RequestBody AuthRolePermissionDTO authRolePermissionDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("RolePermissionController.add authRolePermissionDTO:{}", JSON.toJSONString(authRolePermissionDTO));
            }
            Preconditions.checkArgument(!CollectionUtils.isEmpty(authRolePermissionDTO.getPermissionIds()), "Permission id list is null");
            Preconditions.checkNotNull(authRolePermissionDTO.getRoleId(), "role id is null");

            AuthRolePermissionBO authRolePermissionBO = AuthRolePermissionDTOConverter.INSTANCE.convertDOtoToBO(authRolePermissionDTO);

            return Result.ok(authRolePermissionDomainService.add(authRolePermissionBO));
        } catch (Exception e) {
            log.error("RolePermissionController.add error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }

//    @RequestMapping("update")
//    public Result<Boolean> update(@RequestBody AuthPermissionDTO authPermissionDTO) {
//        try {
//            if (log.isInfoEnabled()) {
//                log.info("PermissionController.update authPermission:{}", JSON.toJSONString(authPermissionDTO));
//            }
//            Preconditions.checkNotNull(authPermissionDTO.getId(), "ID is null");
//            AuthPermissionBO authPermissionBO = AuthPermissionDTOConverter.INSTANCE.convertDOtoToBO(authPermissionDTO);
//            return Result.ok(authRoleDomainService.update(authPermissionBO));
//        } catch (Exception e) {
//            log.error("PermissionController.update error:{}", e.getMessage(), e);
//            return Result.fail(e.getMessage());
//        }
//    }
//    @RequestMapping("changeStatus")
//    public Result<Boolean> changeStatus(@RequestBody AuthPermissionDTO authPermissionDTO) {
//        try {
//            if (log.isInfoEnabled()) {
//                log.info("UserController.changeStatus authUserDTOh:{}", JSON.toJSONString(authPermissionDTO));
//            }
//            Preconditions.checkNotNull(authPermissionDTO.getId(), "ID is null");
//            Preconditions.checkNotNull(authPermissionDTO.getStatus(), "status is null");
//            AuthPermissionBO authPermissionBO = AuthPermissionDTOConverter.INSTANCE.convertDOtoToBO(authPermissionDTO);
//            return Result.ok(authRoleDomainService.update(authPermissionBO));
//        } catch (Exception e) {
//            log.error("UserController.changeStatus error:{}", e.getMessage(), e);
//            return Result.fail(e.getMessage());
//        }
//    }
//
//    @RequestMapping("delete")
//    public Result<Boolean> delete(@RequestBody AuthPermissionDTO authPermissionDTO) {
//        try {
//            if (log.isInfoEnabled()) {
//                log.info("PermissionController.delete authPermissionDTO:{}", JSON.toJSONString(authPermissionDTO));
//            }
//            AuthPermissionBO authPermissionBO = AuthPermissionDTOConverter.INSTANCE.convertDOtoToBO(authPermissionDTO);
//            return Result.ok(authRoleDomainService.delete(authPermissionBO));
//        } catch (Exception e) {
//            log.error("PermissionController.delete error:{}", e.getMessage(), e);
//            return Result.fail(e.getMessage());
//        }
//    }

}
