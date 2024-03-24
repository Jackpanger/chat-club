package com.jackpang.auth.application.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (AuthRole)实体类
 *
 * @author makejava
 * @since 2024-03-24 12:07:55
 */
@Data
public class AuthRoleDTO implements Serializable {
    private static final long serialVersionUID = 228041602112560563L;

    private Long id;
/**
     * 角色名称
     */
    private String roleName;
/**
     * 角色唯一标识
     */
    private String roleKey;

}

