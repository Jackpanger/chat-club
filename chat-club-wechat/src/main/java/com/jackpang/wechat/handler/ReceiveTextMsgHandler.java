package com.jackpang.wechat.handler;

import com.jackpang.wechat.redis.RedisUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Random;

/**
 * description: RecieveTextMsgHandler
 * date: 3/26/24 8:35 PM
 * author: jinhao_pang
 * version: 1.0
 */
@Component
@Slf4j
public class ReceiveTextMsgHandler implements WechatMsgHandler {
    private static final String KEY_WORD = "验证码";
    private static final String LOGIN_PREFIX = "loginCode";
    @Resource
    private RedisUtil redisUtil;

    @Override
    public WechatMsgTypeEnum getMsgType() {
        return WechatMsgTypeEnum.TEXT_MSG;
    }

    @Override
    public String dealMsg(Map<String, String> messageMap) {
        log.info("deal user text msg!!");
        String content = messageMap.get("Content");
        if (!KEY_WORD.equals(content)) {
            return "";
        }
        String fromUserName = messageMap.get("FromUserName");
        String toUserName = messageMap.get("ToUserName");
        Random random = new Random();
//        String codeKey = redisUtil.buildKey(LOGIN_PREFIX, fromUserName, String.valueOf(code));
//        redisUtil.setNx(codeKey, "1", 5L, java.util.concurrent.TimeUnit.MINUTES);
        int code = 1000 + random.nextInt(9000);
        String codeKey = redisUtil.buildKey(LOGIN_PREFIX,  String.valueOf(code));
        while (redisUtil.exist(codeKey)) {
            code = 1000 + random.nextInt(9000);
            codeKey = redisUtil.buildKey(LOGIN_PREFIX,  String.valueOf(code));
        }
        redisUtil.setNx(codeKey, fromUserName, 5L, java.util.concurrent.TimeUnit.MINUTES);
        String codeContent = "您的验证码是：" + code + "，请不要泄露给他人！5分钟内有效！";
        String replyContent = "<xml>\n" +
                "  <ToUserName><![CDATA[" + fromUserName + "]]></ToUserName>\n" +
                "  <FromUserName><![CDATA[" + toUserName + "]]></FromUserName>\n" +
                "  <CreateTime>12345678</CreateTime>\n" +
                "  <MsgType><![CDATA[text]]></MsgType>\n" +
                "  <Content><![CDATA[" + codeContent + "]]></Content>\n" +
                "</xml>\n";
        return replyContent;
    }
}
