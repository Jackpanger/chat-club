package com.jackpang.auth.common.enums;

import lombok.Getter;

/**
 * description: ResultCode
 * date: 3/10/24 4:30 PM
 * author: jinhao_pang
 * version: 1.0
 */
@Getter
public enum ResultCodeEnum {
    SUCCESS(200, "success"),
    FAIL(500, "fail");

    private int code;
    private String desc;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.desc = message;
    }
    public static ResultCodeEnum getByCode(int code) {
        for (ResultCodeEnum resultCodeEnum : ResultCodeEnum.values()) {
            if (resultCodeEnum.getCode() == code) {
                return resultCodeEnum;
            }
        }
        return null;
    }
}
