package com.jackpang.auth.domain.service;

import com.jackpang.auth.domain.entity.AuthUserBO;

 import java.util.List;

/**
 * description: AuthUserDomainService
 * date: 3/10/24 3:12 AM
 * author: jinhao_pang
 * version: 1.0
 */
public interface AuthUserDomainService {
    /**
     * Add primary category
     */
    Boolean register(AuthUserBO authUserBO);

//    /**
//     * Query primary category
//     */
//    List<AuthUserBO> queryCategory(AuthUserBO authUserBO);
//
//    /**
//     * update
//     * @param authUserBO
//     */
//    Boolean update(AuthUserBO authUserBO);
//
//    Boolean delete(AuthUserBO authUserBO);
}
