package com.jackpang.auth.domain.service;

import com.jackpang.auth.domain.entity.AuthPermissionBO;
import com.jackpang.auth.domain.entity.AuthRoleBO;

/**
 * description: AuthUserDomainService
 * date: 3/10/24 3:12 AM
 * author: jinhao_pang
 * version: 1.0
 */
public interface AuthPermissionDomainService {
    /**
     * Add primary category
     */
    Boolean add(AuthPermissionBO authPermissionBO);
    /**
     * update
     * @param authPermissionBO
     */
    Boolean update(AuthPermissionBO authPermissionBO);

    Boolean delete(AuthPermissionBO authPermissionBO);
}
