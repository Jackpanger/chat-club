package com.jackpang.wechat.handler;

public enum WechatMsgTypeEnum {
    SUBSCRIBE("event.subscribe", "用户关注事件"),
    TEXT_MSG("text", "接收文本消息");

    private String msgType;
    private String desc;

    WechatMsgTypeEnum(String msgType, String desc) {
        this.msgType = msgType;
        this.desc = desc;
    }

    public static WechatMsgTypeEnum getByMsgType(String msgType) {
        for (WechatMsgTypeEnum wechatMsgTypeEnum : WechatMsgTypeEnum.values()) {
            if (wechatMsgTypeEnum.msgType.equals(msgType)) {
                return wechatMsgTypeEnum;
            }
        }
        return null;
    }
}
