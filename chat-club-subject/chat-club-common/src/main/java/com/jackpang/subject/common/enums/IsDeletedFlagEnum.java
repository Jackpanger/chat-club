package com.jackpang.subject.common.enums;

import lombok.Getter;

/**
 * description: deleted status enum
 * date: 3/10/24 4:30 PM
 * author: jinhao_pang
 * version: 1.0
 */
@Getter
public enum IsDeletedFlagEnum {
    DELETED(1, "deleted"),
    UNDELETED(0, "undeleted");

    private int code;
    private String desc;

    IsDeletedFlagEnum(Integer code, String message) {
        this.code = code;
        this.desc = message;
    }
    public static IsDeletedFlagEnum getByCode(int code) {
        for (IsDeletedFlagEnum resultCodeEnum : IsDeletedFlagEnum.values()) {
            if (resultCodeEnum.getCode() == code) {
                return resultCodeEnum;
            }
        }
        return null;
    }
}
