package com.jackpang.subject.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.jackpang.subject.common.entity.PageResult;
import com.jackpang.subject.common.enums.IsDeletedFlagEnum;
import com.jackpang.subject.domain.convert.SubjectInfoConverter;
import com.jackpang.subject.domain.entity.SubjectInfoBO;
import com.jackpang.subject.domain.entity.SubjectOptionBO;
import com.jackpang.subject.domain.handler.subject.SubjectTypeHandler;
import com.jackpang.subject.domain.handler.subject.SubjectTypeHandlerFactory;
import com.jackpang.subject.domain.service.SubjectInfoDomainService;
import com.jackpang.subject.infra.basic.entity.SubjectInfo;
import com.jackpang.subject.infra.basic.entity.SubjectLabel;
import com.jackpang.subject.infra.basic.entity.SubjectMapping;
import com.jackpang.subject.infra.basic.service.SubjectInfoService;
import com.jackpang.subject.infra.basic.service.SubjectLabelService;
import com.jackpang.subject.infra.basic.service.SubjectMappingService;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * description: SubjectInfoDomainServiceImpl
 * date: 3/10/24 3:13 AM
 * author: jinhao_pang
 * version: 1.0
 */
@Service
@Slf4j
public class SubjectInfoDomainServiceImpl implements SubjectInfoDomainService {

    @Resource
    private SubjectInfoService subjectInfoService;
    @Resource
    private SubjectTypeHandlerFactory subjectTypeHandlerFactory;
    @Resource
    private SubjectMappingService subjectMappingService;
    @Resource
    private SubjectLabelService subjectLabelService;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(SubjectInfoBO subjectInfoBO) {
        if (log.isInfoEnabled()) {
            log.info("SubjectInfoDomainServiceImpl.add SubjectLabelBO:{}", JSON.toJSONString(subjectInfoBO));
        }
        SubjectInfo subjectInfo = SubjectInfoConverter.INSTANCE.convertBoToInfo(subjectInfoBO);
        subjectInfo.setIsDeleted(IsDeletedFlagEnum.NOT_DELETED.getCode());
        subjectInfoService.insert(subjectInfo);
        SubjectTypeHandler handler = subjectTypeHandlerFactory.getHandler(subjectInfo.getSubjectType());
        subjectInfoBO.setId(subjectInfo.getId());
        handler.add(subjectInfoBO);
        List<Long> categoryIds = subjectInfoBO.getCategoryIds();
        List<Long> labelIds = subjectInfoBO.getLabelIds();
        List<SubjectMapping> subjectMappings = new LinkedList<>();
        categoryIds.forEach(categoryId -> {
            labelIds.forEach(labelId -> {
                SubjectMapping subjectMapping = new SubjectMapping();
                subjectMapping.setSubjectId(subjectInfo.getId());
                subjectMapping.setCategoryId(categoryId);
                subjectMapping.setLabelId(labelId);
                subjectMapping.setIsDeleted(IsDeletedFlagEnum.NOT_DELETED.getCode());
                subjectMappings.add(subjectMapping);
            });
        });
        subjectMappingService.insertBatch(subjectMappings);

    }

    @Override
    public PageResult<SubjectInfoBO> getSubjectPage(SubjectInfoBO subjectInfoBO) {
        PageResult<SubjectInfoBO> pageResult = new PageResult<>();
        pageResult.setPageNum(subjectInfoBO.getPageNum());
        pageResult.setPageSize(subjectInfoBO.getPageSize());
        int start = (subjectInfoBO.getPageNum() - 1) * subjectInfoBO.getPageSize();
        SubjectInfo subjectInfo = SubjectInfoConverter.INSTANCE.convertBoToInfo(subjectInfoBO);
        int count = subjectInfoService.countByCondition(subjectInfo, subjectInfoBO.getCategoryId(), subjectInfoBO.getLabelId());
        if (count == 0) return pageResult;
        List<SubjectInfo> subjectInfoList = subjectInfoService.queryPage(subjectInfo, subjectInfoBO.getCategoryId(), subjectInfoBO.getLabelId(), start, subjectInfoBO.getPageSize());
        List<SubjectInfoBO> subjectInfoBOS = SubjectInfoConverter.INSTANCE.convertBoToInfoList(subjectInfoList);
        pageResult.setRecords(subjectInfoBOS);
        pageResult.setTotal(count);
        return pageResult;
    }

    @Override
    public SubjectInfoBO querySubjectInfo(SubjectInfoBO subjectInfoBO) {
        SubjectInfo subjectInfo = subjectInfoService.queryById(subjectInfoBO.getId());
        Integer subjectType = subjectInfo.getSubjectType();
        SubjectTypeHandler handler = subjectTypeHandlerFactory.getHandler(subjectType);
        SubjectOptionBO optionBO = handler.query(subjectInfoBO.getId());
        SubjectInfoBO bo = SubjectInfoConverter.INSTANCE.concatOptionAndInfo(optionBO, subjectInfo);
        SubjectMapping subjectMapping = new SubjectMapping();
        subjectMapping.setSubjectId(subjectInfoBO.getId());
        subjectMapping.setIsDeleted(IsDeletedFlagEnum.NOT_DELETED.getCode());
        List<SubjectMapping> subjectMappings = subjectMappingService.queryLabelId(subjectMapping);
        List<Long> labelIdList = subjectMappings.stream().map(SubjectMapping::getLabelId).collect(Collectors.toList());
        List<SubjectLabel> labelList = subjectLabelService.batchQueryById(labelIdList);
        List<String> labelNamesList = labelList.stream().map(SubjectLabel::getLabelName).collect(Collectors.toList());
        bo.setLabelName(labelNamesList);
        return bo;
    }
}
