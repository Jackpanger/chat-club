package com.jackpang.subject.common.enums;

import lombok.Getter;

/**
 * description: CategoryTypeEnum
 * date: 3/10/24 4:30 PM
 * author: jinhao_pang
 * version: 1.0
 */
@Getter
public enum CategoryTypeEnum {
    Primary(1, "Primary position"),
    Secondary(500, "secondary category");

    private int code;
    private String desc;

    CategoryTypeEnum(Integer code, String message) {
        this.code = code;
        this.desc = message;
    }
    public static CategoryTypeEnum getByCode(int code) {
        for (CategoryTypeEnum resultCodeEnum : CategoryTypeEnum.values()) {
            if (resultCodeEnum.getCode() == code) {
                return resultCodeEnum;
            }
        }
        return null;
    }
}
