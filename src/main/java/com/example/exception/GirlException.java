package com.example.exception;

import com.example.enums.ResultEnum;

/**
 * 自定义异常
 */
public class GirlException extends RuntimeException {
    /** 错误码 */
    private Integer code;

    public GirlException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
