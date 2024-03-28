package com.jackpang.oss.entity;

import lombok.Data;

/**
 * description: Result
 * date: 3/10/24 4:21 PM
 * author: jinhao_pang
 * version: 1.0
 */
@Data
public class Result<T> {
    private Boolean success;
    private Integer code;
    private String message;
    private T data;
    public static <T> Result<T> ok() {
        return ok(null);
    }

    public  static <T>  Result ok(T data) {
        Result result = new Result<>();
        result.setSuccess(true);
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getDesc());
        result.setData(data);
        return result;
    }

    public static <T> Result<T> fail() {
        return fail(null);
    }

    public  static <T>  Result fail(T data) {
        Result result = new Result<>();
        result.setSuccess(false);
        result.setCode(ResultCodeEnum.FAIL.getCode());
        result.setMessage(ResultCodeEnum.FAIL.getDesc());
        result.setData(data);
        return result;
    }
}
