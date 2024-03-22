package com.jackpang.subject.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.jackpang.subject.common.enums.IsDeletedFlagEnum;
import com.jackpang.subject.domain.convert.SubjectCategoryConverter;
import com.jackpang.subject.domain.entity.SubjectCategoryBO;
import com.jackpang.subject.domain.service.SubjectCategoryDomainService;
import com.jackpang.subject.infra.basic.entity.SubjectCategory;
import com.jackpang.subject.infra.basic.service.SubjectCategoryService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * description: SubjectCategoryDomainServiceImpl
 * date: 3/10/24 3:13 AM
 * author: jinhao_pang
 * version: 1.0
 */
@Service
@Slf4j
public class SubjectCategoryDomainServiceImpl implements SubjectCategoryDomainService {
    @Resource
    private SubjectCategoryService subjectCategoryService;

    @Override
    public void add(SubjectCategoryBO SubjectCategoryBO) {
        if (log.isInfoEnabled()) {
            log.info("SubjectCategoryDomainServiceImpl.add SubjectCategoryBO:{}", JSON.toJSONString(SubjectCategoryBO));
        }
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE.convertBoToCategory(SubjectCategoryBO);
        subjectCategoryService.insert(subjectCategory);
    }

    @Override
    public List<SubjectCategoryBO> queryCategory(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE.convertBoToCategory(subjectCategoryBO);
        subjectCategory.setIsDeleted(IsDeletedFlagEnum.NOT_DELETED.getCode());
        List<SubjectCategory> subjectCategoryList = subjectCategoryService.queryCategory(subjectCategory);
        List<SubjectCategoryBO> subjectCategoryBOList = SubjectCategoryConverter.INSTANCE.convertCategoryListToBoList(subjectCategoryList);
        if (log.isInfoEnabled()) {
            log.info("SubjectCategoryDomainServiceImpl.queryCategory SubjectCategoryBOList:{}", JSON.toJSONString(subjectCategoryBOList));
        }
        return subjectCategoryBOList;

    }

    @Override
    public Boolean update(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE.convertBoToCategory(subjectCategoryBO);
        int count = subjectCategoryService.update(subjectCategory);
        return count > 0;
    }

    @Override
    public Boolean delete(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE.convertBoToCategory(subjectCategoryBO);
        subjectCategory.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        int count = subjectCategoryService.update(subjectCategory);
        return count > 0;
    }

}
