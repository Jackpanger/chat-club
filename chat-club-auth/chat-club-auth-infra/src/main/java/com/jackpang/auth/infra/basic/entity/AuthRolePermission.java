package com.jackpang.auth.infra.basic.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 角色权限关联表(AuthRolePermission)实体类
 *
 * @author makejava
 * @since 2024-03-24 15:23:28
 */
@Data
public class AuthRolePermission implements Serializable {
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
/**
     * 创建人
     */
    private String createdBy;
/**
     * 创建时间
     */
    private Date createdTime;
/**
     * 更新人
     */
    private String updateBy;
/**
     * 更新时间
     */
    private Date updateTime;

    private Integer isDeleted;

}

