package com.jackpang.subject.application.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.jackpang.subject.application.convert.SubjectLabelDTOConverter;
import com.jackpang.subject.application.dto.SubjectLabelDTO;
import com.jackpang.subject.common.entity.Result;
import com.jackpang.subject.domain.entity.SubjectLabelBO;
import com.jackpang.subject.domain.service.SubjectLabelDomainService;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * description: SubjectLabelController
 * date: 3/12/24 7:05 PM
 * author: jinhao_pang
 * version: 1.0
 */
@RestController
@RequestMapping("/subject/label")
@Slf4j
public class SubjectLabelController {

    @Resource
    private SubjectLabelDomainService subjectLabelDomainService;

    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody SubjectLabelDTO subjectLabelDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectLabelController.add subjectLabelDTO:{}", JSON.toJSONString(subjectLabelDTO));
            }
            Preconditions.checkNotNull(subjectLabelDTO.getLabelName(), "Label name is null");
            SubjectLabelBO subjectLabelBO = SubjectLabelDTOConverter.INSTANCE.convertDOtoToBO(subjectLabelDTO);
            Boolean result = subjectLabelDomainService.add(subjectLabelBO);
            return Result.ok(result);
        } catch (Exception e) {
            log.error("SubjectLabelController.add error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }

    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody SubjectLabelDTO subjectLabelDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectLabelController.update subjectLabelDTO:{}", JSON.toJSONString(subjectLabelDTO));
            }
            Preconditions.checkNotNull(subjectLabelDTO.getId(), "ID is null");
            SubjectLabelBO subjectLabelBO = SubjectLabelDTOConverter.INSTANCE.convertDOtoToBO(subjectLabelDTO);
            Boolean result = subjectLabelDomainService.update(subjectLabelBO);
            return Result.ok(result);
        } catch (Exception e) {
            log.error("SubjectLabelController.update error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }

    @PostMapping("/delete")
    public Result<Boolean> delete(@RequestBody SubjectLabelDTO subjectLabelDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectLabelController.delete subjectLabelDTO:{}", JSON.toJSONString(subjectLabelDTO));
            }
            Preconditions.checkNotNull(subjectLabelDTO.getId(), "ID is null");
            SubjectLabelBO subjectLabelBO = SubjectLabelDTOConverter.INSTANCE.convertDOtoToBO(subjectLabelDTO);
            Boolean result = subjectLabelDomainService.delete(subjectLabelBO);
            return Result.ok(result);
        } catch (Exception e) {
            log.error("SubjectLabelController.delete error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }

    /**
     * query label by category id
     *
     * @param subjectLabelDTO
     * @return
     */
    @PostMapping("/queryLabelByCategoryId")
    public Result<List<SubjectLabelDTO>> queryLabelByCategoryId(@RequestBody SubjectLabelDTO subjectLabelDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectLabelController.queryLabelByCategoryId subjectLabelDTO:{}", JSON.toJSONString(subjectLabelDTO));
            }
            Preconditions.checkNotNull(subjectLabelDTO.getCategoryId(), "Category ID is null");
            SubjectLabelBO subjectLabelBO = SubjectLabelDTOConverter.INSTANCE.convertDOtoToBO(subjectLabelDTO);
            List<SubjectLabelBO> subjectLabelBOList = subjectLabelDomainService.queryLabelByCategoryId(subjectLabelBO);
            List<SubjectLabelDTO> result = SubjectLabelDTOConverter.INSTANCE.convertBOListToDTOList(subjectLabelBOList);
            return Result.ok(result);
        } catch (Exception e) {
            log.error("SubjectLabelController.queryLabelByCategoryId error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }


}
