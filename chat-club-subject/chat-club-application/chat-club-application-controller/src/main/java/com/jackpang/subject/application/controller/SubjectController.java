package com.jackpang.subject.application.controller;

import com.jackpang.subject.infra.basic.entity.SubjectCategory;
import com.jackpang.subject.infra.basic.service.SubjectCategoryService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description: SubjectController
 * date: 3/9/24 8:13 PM
 * author: jinhao_pang
 * version: 1.0
 */
@RestController
public class SubjectController {
    @Resource
    private SubjectCategoryService subjectCategoryService;
    @GetMapping("/test")
    public String test() {
        SubjectCategory subjectCategory = subjectCategoryService.queryById(1L);
        return subjectCategory.getCategoryName();
    }
}
