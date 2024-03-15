package com.jackpang.subject.domain.handler.subject;

import com.jackpang.subject.common.enums.SubjectInfoTypeEnum;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description: SubjectTypeHandlerFactory
 * date: 3/14/24 12:21 AM
 * author: jinhao_pang
 * version: 1.0
 */
@Component
public class SubjectTypeHandlerFactory implements InitializingBean {
    @Resource
    private List<SubjectTypeHandler> subjectTypeHandlers;

    private Map<SubjectInfoTypeEnum, SubjectTypeHandler> subjectTypeHandlerMap = new HashMap<>();

    public SubjectTypeHandler getHandler( int subjectType) {
        SubjectInfoTypeEnum subjectInfoTypeEnum = SubjectInfoTypeEnum.getByCode(subjectType);
        return subjectTypeHandlerMap.get(subjectInfoTypeEnum);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        for (SubjectTypeHandler subjectTypeHandler : subjectTypeHandlers) {
            subjectTypeHandlerMap.put(subjectTypeHandler.getHandlerType(), subjectTypeHandler);
        }
    }
}
