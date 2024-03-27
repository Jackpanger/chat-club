package com.jackpang.wechat.controller;

import com.jackpang.wechat.handler.WechatMsgFactory;
import com.jackpang.wechat.handler.WechatMsgHandler;
import com.jackpang.wechat.utils.MessageUtil;
import com.jackpang.wechat.utils.SHA1;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * description: CallbackController
 * date: 3/26/24 4:05 PM
 * author: jinhao_pang
 * version: 1.0
 */
@RestController
@Slf4j
public class CallbackController {
    private static final String TOKEN = "tokenjackpangwechat";
    @Resource
    private WechatMsgFactory wechatMsgFactory;

    private static final String CACHE_KEY_SEPARATOR = ".";
    @RequestMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/callback")
    public String callback(@RequestParam("signature") String signature,
                           @RequestParam("timestamp") String timestamp,
                           @RequestParam("nonce") String nonce,
                           @RequestParam("echostr") String echostr) {
        log.info("Get a callback request, signature: {}, timestamp: {}, nonce: {}, echostr: {}",
                signature, timestamp, nonce, echostr);
        String shaStr = SHA1.getSHA1(TOKEN, timestamp, nonce, "");
        if (signature.equals(shaStr)) {
            return echostr;
        }
        return "unauthorized request";
    }

    @PostMapping(value = "/callback", produces = "application/xml; charset=UTF-8")
    public String callback(
            @RequestBody String requestBody,
            @RequestParam("signature") String signature,
            @RequestParam("timestamp") String timestamp,
            @RequestParam("nonce") String nonce,
            @RequestParam(value = "msg_signature", required = false) String msgSignature) {
        log.info("Post a callback request, requestBody: {}, signature: {}, timestamp: {}, nonce: {}, msg_signature: {}",
                requestBody, signature, timestamp, nonce, msgSignature);
        Map<String, String> msgMap = MessageUtil.parseXml(requestBody);
        String msgType = msgMap.get("MsgType");
        String event = msgMap.get("Event") == null ? "" : msgMap.get("Event");
        log.info("msgType: {}, event: {}", msgType, event);
        StringBuilder sb = new StringBuilder();
        sb.append(msgType);
        if(!StringUtils.isBlank(event)) {
            sb.append(".");
            sb.append(event);
        }
        WechatMsgHandler handler = wechatMsgFactory.getHandlerByMsgType(sb.toString());
        if (Objects.isNull(handler)) {
            log.error("Unsupported msgType: {}", msgType);
            return "unknown msgType";
        }
        String replyContent = handler.dealMsg(msgMap);
        log.info("replyContent: {}", replyContent);
        return replyContent;
    }

//    String msg = "<xml>\n" +
//            "  <ToUserName><![CDATA[" + fromUserName + "]]></ToUserName>\n" +
//            "  <FromUserName><![CDATA[" + toUserName + "]]></FromUserName>\n" +
//            "  <CreateTime>12345678</CreateTime>\n" +
//            "  <MsgType><![CDATA[text]]></MsgType>\n" +
//            "  <Content><![CDATA[你好,welcome]]></Content>\n" +
//            "</xml>\n";

//        <xml><ToUserName><![CDATA[gh_ff80567f1082]]></ToUserName>
//        <FromUserName><![CDATA[ouI5_6HQMhS8wiG1cGPseB_ANqsU]]></FromUserName>
//        <CreateTime>1711496618</CreateTime>
//        <MsgType><![CDATA[event]]></MsgType>
//        <Event><![CDATA[subscribe]]></Event>
//        <EventKey><![CDATA[]]></EventKey>
//        </xml>

//    <xml><ToUserName><![CDATA[gh_ff80567f1082]]></ToUserName>
//    <FromUserName><![CDATA[ouI5_6HQMhS8wiG1cGPseB_ANqsU]]></FromUserName>
//    <CreateTime>1711496625</CreateTime>
//    <MsgType><![CDATA[text]]></MsgType>
//    <Content><![CDATA[你好]]></Content>
//    <MsgId>24502738921350855</MsgId>
//    </xml>

    public String buildKey(String... strObjs) {
        return String.join(CACHE_KEY_SEPARATOR, strObjs);
    }
}
