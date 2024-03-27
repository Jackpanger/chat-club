package com.jackpang.wechat.handler;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description: WechatMsgFactory
 * date: 3/26/24 8:36 PM
 * author: jinhao_pang
 * version: 1.0
 */
@Component
public class WechatMsgFactory implements InitializingBean {
    @Resource
    private List<WechatMsgHandler> wechatMsgHandlerList;
    private Map<WechatMsgTypeEnum, WechatMsgHandler> handlerMap = new HashMap<>();

    public WechatMsgHandler getHandlerByMsgType(String msgType) {
        WechatMsgTypeEnum msgTypeEnum = WechatMsgTypeEnum.getByMsgType(msgType);
        return handlerMap.get(msgTypeEnum);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        for (WechatMsgHandler wechatMsgHandler : wechatMsgHandlerList) {
            handlerMap.put(wechatMsgHandler.getMsgType(), wechatMsgHandler);
        }
    }
}
