package com.jackpang.wechat.handler;

import java.util.Map;

public interface WechatMsgHandler {
    WechatMsgTypeEnum getMsgType();
    String dealMsg(Map<String,String> messageMap);
}
