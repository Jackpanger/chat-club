package com.jackpang.subject.common.enums;

import lombok.Getter;

/**
 * description: SubjectInfoTypeEnum
 * 1.radio 2.multiply 3.judge 4 brief
 * date: 3/10/24 4:30 PM
 * author: jinhao_pang
 * version: 1.0
 */
@Getter
public enum SubjectInfoTypeEnum {
    RADIO(1, "Radio"),
    MULTIPLE(2, "MULTIPLE choice"),
    JUDGE(3, "JUDGE question"),
    BRIEF(4, "BRIEF question");

    private int code;
    private String desc;

    SubjectInfoTypeEnum(Integer code, String message) {
        this.code = code;
        this.desc = message;
    }
    public static SubjectInfoTypeEnum getByCode(int code) {
        for (SubjectInfoTypeEnum resultCodeEnum : SubjectInfoTypeEnum.values()) {
            if (resultCodeEnum.getCode() == code) {
                return resultCodeEnum;
            }
        }
        return null;
    }
}
