package com.jackpang.auth.domain.service;

import com.jackpang.auth.domain.entity.AuthPermissionBO;
import com.jackpang.auth.domain.entity.AuthRolePermissionBO;

/**
 * description: AuthUserDomainService
 * date: 3/10/24 3:12 AM
 * author: jinhao_pang
 * version: 1.0
 */
public interface AuthRolePermissionDomainService {
    /**
     * Add primary category
     */
    Boolean add(AuthRolePermissionBO authRolePermissionBO);
//    /**
//     * update
//     * @param authPermissionBO
//     */
//    Boolean update(AuthRolePermissionBO authRolePermissionBO);
//
//    Boolean delete(AuthRolePermissionBO authRolePermissionBO);
}
