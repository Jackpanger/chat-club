package com.jackpang.auth.domain.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import com.alibaba.fastjson.JSON;
import com.jackpang.auth.common.enums.AuthUserStatusEnum;
import com.jackpang.auth.common.enums.IsDeletedFlagEnum;
import com.jackpang.auth.domain.convert.AuthUserBOConverter;
import com.jackpang.auth.domain.entity.AuthUserBO;
import com.jackpang.auth.domain.service.AuthUserDomainService;
import com.jackpang.auth.infra.basic.entity.AuthUser;
import com.jackpang.auth.infra.basic.service.AuthUserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * description: SubjectCategoryDomainServiceImpl
 * date: 3/10/24 3:13 AM
 * author: jinhao_pang
 * version: 1.0
 */
@Service
@Slf4j
public class AuthUserDomainServiceImpl implements AuthUserDomainService {
    @Resource
    private AuthUserService authUserService;
    private String salt = "jack";

    @Override
    public Boolean register(AuthUserBO authUserBO) {
        if (log.isInfoEnabled()) {
            log.info("AuthUserDomainServiceImpl.register authUserBO:{}", JSON.toJSONString(authUserBO));
        }
        AuthUser authUser = AuthUserBOConverter.INSTANCE.convertBOtoToUser(authUserBO);
        authUser.setPassword(SaSecureUtil.md5BySalt(authUser.getPassword(),salt));
        authUser.setStatus(AuthUserStatusEnum.OPEN.getCode());
        authUser.setIsDeleted(IsDeletedFlagEnum.NOT_DELETED.getCode());
        Integer count = authUserService.insert(authUser);
        // set a role
        // put it in the redis
        return count > 0;
    }

//    @Override
//    public List<AuthUserBO> queryCategory(AuthUserBO subjectCategoryBO) {
//        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE.convertBoToCategory(subjectCategoryBO);
//        subjectCategory.setIsDeleted(IsDeletedFlagEnum.NOT_DELETED.getCode());
//        List<SubjectCategory> subjectCategoryList = subjectCategoryService.queryCategory(subjectCategory);
//        List<SubjectCategoryBO> subjectCategoryBOList = SubjectCategoryConverter.INSTANCE.convertCategoryListToBoList(subjectCategoryList);
//        if (log.isInfoEnabled()) {
//            log.info("SubjectCategoryDomainServiceImpl.queryCategory SubjectCategoryBOList:{}", JSON.toJSONString(subjectCategoryBOList));
//        }
//        return subjectCategoryBOList;
//
//    }
//
    @Override
    public Boolean update(AuthUserBO authUserBO) {
        if (log.isInfoEnabled()) {
            log.info("AuthUserDomainServiceImpl.update authUserBO:{}", JSON.toJSONString(authUserBO));
        }
        AuthUser authUser = AuthUserBOConverter.INSTANCE.convertBOtoToUser(authUserBO);
        Integer count = authUserService.update(authUser);
        // put it in the redis
        return count > 0;
    }

    @Override
    public Boolean delete(AuthUserBO authUserBO) {
        if (log.isInfoEnabled()) {
            log.info("AuthUserDomainServiceImpl.delete authUserBO:{}", JSON.toJSONString(authUserBO));
        }

        AuthUser authUser = new AuthUser();
        authUser.setId(authUserBO.getId());
        authUser.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        Integer count = authUserService.update(authUser);
        // put it in the redis
        return count > 0;
    }

}
