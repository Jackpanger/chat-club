package com.jackpang.auth.domain.service;

import com.jackpang.auth.domain.entity.AuthRoleBO;
import com.jackpang.auth.domain.entity.AuthUserBO;

/**
 * description: AuthUserDomainService
 * date: 3/10/24 3:12 AM
 * author: jinhao_pang
 * version: 1.0
 */
public interface AuthRoleDomainService {
    /**
     * Add primary category
     */
    Boolean add(AuthRoleBO authRoleBO);
    /**
     * update
     * @param authRoleBO
     */
    Boolean update(AuthRoleBO authRoleBO);

    Boolean delete(AuthRoleBO authRoleBO);
}
