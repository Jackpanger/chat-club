package com.jackpang.wechat.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * description: SubscribeMsgHandler
 * date: 3/26/24 8:34 PM
 * author: jinhao_pang
 * version: 1.0
 */
@Slf4j
@Component
public class SubscribeMsgHandler implements WechatMsgHandler {
    @Override
    public WechatMsgTypeEnum getMsgType() {
        return WechatMsgTypeEnum.SUBSCRIBE;
    }

    @Override
    public String dealMsg(Map<String, String> messageMap) {
        log.info("deal user subscribe msg!!");
        String fromUserName = messageMap.get("FromUserName");
        String toUserName = messageMap.get("ToUserName");
        String subscribeContent = "欢迎关注我的公众号，我是JackPang! Welcome to subscribe my wechat public account!";
        String content = "<xml>\n" +
                "  <ToUserName><![CDATA[" + fromUserName + "]]></ToUserName>\n" +
                "  <FromUserName><![CDATA[" + toUserName + "]]></FromUserName>\n" +
                "  <CreateTime>12345678</CreateTime>\n" +
                "  <MsgType><![CDATA[text]]></MsgType>\n" +
                "  <Content><![CDATA[" + subscribeContent + "]]></Content>\n" +
                "</xml>\n";
        return content;
    }
}
