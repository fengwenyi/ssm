package com.fengwenyi.demo.ssm.config;

import com.fengwenyi.javalib.result.IReturnCode;

public enum ReturnCode implements IReturnCode {

    ERROR(100, "Error"),

    SUCCESS(0, "Success")

    ;

    private Integer code;
    private String msg;

    ReturnCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
