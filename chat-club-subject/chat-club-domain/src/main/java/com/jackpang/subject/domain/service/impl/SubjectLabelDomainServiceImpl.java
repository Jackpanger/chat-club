package com.jackpang.subject.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.jackpang.subject.common.enums.IsDeletedFlagEnum;
import com.jackpang.subject.convert.SubjectLabelConverter;
import com.jackpang.subject.domain.entity.SubjectLabelBO;
import com.jackpang.subject.domain.service.SubjectLabelDomainService;
import com.jackpang.subject.infra.basic.entity.SubjectLabel;
import com.jackpang.subject.infra.basic.entity.SubjectMapping;
import com.jackpang.subject.infra.basic.service.SubjectLabelService;
import com.jackpang.subject.infra.basic.service.SubjectMappingService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * description: SubjectCategoryDomainServiceImpl
 * date: 3/10/24 3:13 AM
 * author: jinhao_pang
 * version: 1.0
 */
@Service
@Slf4j
public class SubjectLabelDomainServiceImpl implements SubjectLabelDomainService {
    @Resource
    private SubjectLabelService subjectLabelService;
    @Resource
    private SubjectMappingService subjectMappingService;

    @Override
    public Boolean add(SubjectLabelBO SubjectLabelBO) {
        if (log.isInfoEnabled()) {
            log.info("SubjectCategoryController.add SubjectLabelBO:{}", JSON.toJSONString(SubjectLabelBO));
        }
        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE.convertBoToLabel(SubjectLabelBO);
        subjectLabel.setIsDeleted(IsDeletedFlagEnum.UNDELETED.getCode());
        return subjectLabelService.insert(subjectLabel) > 0;
    }

    @Override
    public Boolean update(SubjectLabelBO subjectLabelBO) {
        if (log.isInfoEnabled()) {
            log.info("SubjectCategoryController.update SubjectLabelBO:{}", JSON.toJSONString(subjectLabelBO));
        }
        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE.convertBoToLabel(subjectLabelBO);
        return subjectLabelService.update(subjectLabel) > 0;
    }

    @Override
    public Boolean delete(SubjectLabelBO subjectLabelBO) {
        if (log.isInfoEnabled()) {
            log.info("SubjectCategoryController.delete SubjectLabelBO:{}", JSON.toJSONString(subjectLabelBO));
        }
        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE.convertBoToLabel(subjectLabelBO);
        subjectLabel.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        return subjectLabelService.update(subjectLabel) > 0;
    }

    @Override
    public List<SubjectLabelBO> queryLabelByCategoryId(SubjectLabelBO subjectLabelBO) {
        if (log.isInfoEnabled()) {
            log.info("SubjectCategoryController.queryLabelByCategoryId SubjectLabelBO:{}", JSON.toJSONString(subjectLabelBO));
        }
        Long categoryId = subjectLabelBO.getCategoryId();
        SubjectMapping subjectMapping = new SubjectMapping();
        subjectMapping.setCategoryId(categoryId);
        subjectMapping.setIsDeleted(IsDeletedFlagEnum.UNDELETED.getCode());
        List<SubjectMapping> subjectMappingList = subjectMappingService.queryLabelId(subjectMapping);
        if (CollectionUtils.isEmpty(subjectMappingList)) {
            return Collections.emptyList();
        }
        List<Long> labelIdList = subjectMappingList.stream().map(SubjectMapping::getLabelId).collect(Collectors.toList());
        List<SubjectLabel> subjectLabelBOList = subjectLabelService.batchQueryById(labelIdList);
        LinkedList<SubjectLabelBO> boList = new LinkedList<>();
        subjectLabelBOList.forEach(subjectLabel -> {
            SubjectLabelBO bo = new SubjectLabelBO();
            bo.setId(subjectLabel.getId());
            bo.setLabelName(subjectLabel.getLabelName());
            bo.setSortNum(subjectLabel.getSortNum());
            bo.setCategoryId(categoryId);
            boList.add(bo);
        });
        return boList;
    }


}
