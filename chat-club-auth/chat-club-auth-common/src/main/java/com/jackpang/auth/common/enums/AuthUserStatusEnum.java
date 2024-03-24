package com.jackpang.auth.common.enums;

import lombok.Getter;

/**
 * description: AuthUserStatusEnum
 * date: 3/10/24 4:30 PM
 * author: jinhao_pang
 * version: 1.0
 */
@Getter
public enum AuthUserStatusEnum {
    OPEN(0, "open"),
    CLOSE(1, "close");

    private int code;
    private String desc;

    AuthUserStatusEnum(Integer code, String message) {
        this.code = code;
        this.desc = message;
    }
    public static AuthUserStatusEnum getByCode(int code) {
        for (AuthUserStatusEnum resultCodeEnum : AuthUserStatusEnum.values()) {
            if (resultCodeEnum.getCode() == code) {
                return resultCodeEnum;
            }
        }
        return null;
    }
}
