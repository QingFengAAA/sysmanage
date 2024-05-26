package com.yhtech.sysmanage.common.dto;

import lombok.Data;

/**
 * @author chenjl
 * @date 2024/5/25 18:33
 * @desc
 */
@Data
public class Result <T> {

    private String code = "000000";
    private String msg = "成功";
    private T data;

    public Result(T data) {
        this.data = data;
    }

    public Result(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
