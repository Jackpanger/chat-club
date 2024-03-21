package com.jackpang.subject.application.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.google.common.base.Preconditions;
import com.jackpang.subject.application.convert.SubjectAnswerDTOConverter;
import com.jackpang.subject.application.convert.SubjectInfoDTOConverter;
import com.jackpang.subject.application.dto.SubjectInfoDTO;
import com.jackpang.subject.common.entity.PageResult;
import com.jackpang.subject.common.entity.Result;
import com.jackpang.subject.domain.entity.SubjectAnswerBO;
import com.jackpang.subject.domain.entity.SubjectInfoBO;
import com.jackpang.subject.domain.service.SubjectInfoDomainService;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * description: SubjectController
 * date: 3/9/24 8:13 PM
 * author: jinhao_pang
 * version: 1.0
 */
@RestController
@Slf4j
@RequestMapping("/subject")
public class SubjectController {
    @Resource
    private SubjectInfoDomainService subjectInfoDomainService;

    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody SubjectInfoDTO subjectInfoDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectController.add subjectInfoDTO:{}", JSON.toJSONString(subjectInfoDTO));
            }
            Preconditions.checkNotNull(subjectInfoDTO.getSubjectType(), "SubjectType is null");
            Preconditions.checkArgument(!StringUtils.isBlank(subjectInfoDTO.getSubjectName()), "Subject name is null");
            Preconditions.checkNotNull(subjectInfoDTO.getSubjectDifficult(), "Difficulty is null");
            Preconditions.checkNotNull(subjectInfoDTO.getSubjectScore(), "SubjectScore is null");
            Preconditions.checkArgument(!CollectionUtils.isEmpty(subjectInfoDTO.getCategoryIds()), "category id is null");
            Preconditions.checkArgument(!CollectionUtils.isEmpty(subjectInfoDTO.getLabelIds()), "label id is null");

            SubjectInfoBO subjectInfoBO = SubjectInfoDTOConverter.INSTANCE.convertDOtoToBO(subjectInfoDTO);
            List<SubjectAnswerBO> subjectAnswerBOS = SubjectAnswerDTOConverter.INSTANCE.convertDTOListToBO(subjectInfoDTO.getOptionList());
            subjectInfoBO.setOptionList(subjectAnswerBOS);
            subjectInfoDomainService.add(subjectInfoBO);
            return Result.ok(true);
        } catch (Exception e) {
            log.error("SubjectController.add error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }

    @PostMapping("/getSubjectPage")
    public Result<PageResult<SubjectInfoDTO>> getSubjectPage(@RequestBody SubjectInfoDTO subjectInfoDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectController.getSubjectPage subjectInfoDTO:{}", JSON.toJSONString(subjectInfoDTO));
            }
            Preconditions.checkNotNull(subjectInfoDTO.getCategoryId(), "Category ID is null");
            Preconditions.checkNotNull(subjectInfoDTO.getLabelId(), "Label ID is null");

            SubjectInfoBO subjectInfoBO = SubjectInfoDTOConverter.INSTANCE.convertDOtoToBO(subjectInfoDTO);
            PageResult<SubjectInfoBO> boPageResult = subjectInfoDomainService.getSubjectPage(subjectInfoBO);
            return Result.ok(boPageResult);
        } catch (Exception e) {
            log.error("SubjectController.getSubjectPage error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }

    @PostMapping("/getSubjectInfo")
    public Result<SubjectInfoDTO> getSubjectInfo(@RequestBody SubjectInfoDTO subjectInfoDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectController.getSubjectInfo subjectInfoDTO:{}", JSON.toJSONString(subjectInfoDTO));
            }
            Preconditions.checkNotNull(subjectInfoDTO.getId(), " ID is null");

            SubjectInfoBO subjectInfoBO = SubjectInfoDTOConverter.INSTANCE.convertDOtoToBO(subjectInfoDTO);
            SubjectInfoBO boPageResult = subjectInfoDomainService.querySubjectInfo(subjectInfoBO);
            SubjectInfoDTO result = SubjectInfoDTOConverter.INSTANCE.convertBOtoToDTO(boPageResult);
            return Result.ok(result);
        } catch (Exception e) {
            log.error("SubjectController.getSubjectInfo error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }
}
