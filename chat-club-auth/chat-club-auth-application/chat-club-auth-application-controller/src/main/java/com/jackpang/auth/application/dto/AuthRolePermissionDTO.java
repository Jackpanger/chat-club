package com.jackpang.auth.application.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 角色权限关联表(AuthRolePermission)实体类
 *
 * @author makejava
 * @since 2024-03-24 15:23:28
 */
@Data
public class AuthRolePermissionDTO implements Serializable {
    private static final long serialVersionUID = 398018542537451715L;

    private Long id;
    /**
     * 角色id
     */
    private Long roleId;
    /**
     * 权限id
     */
    private Long permissionId;

    private List<Long> permissionIds;

}

