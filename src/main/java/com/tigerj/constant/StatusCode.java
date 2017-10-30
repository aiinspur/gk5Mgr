package com.tigerj.constant;

import java.util.HashMap;
import java.util.Map;


public enum StatusCode {

    /**
     * 1-999 系统预留
     **/
    SUCCESS(0, "成功"),
    SYSTEM_ERR(-1, "系统异常"),
    CODE_REPEAT(1001, "编码已存在"),;
	
	
	
    private final int code;
    private final String desc;

    StatusCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }

    private static final Map<Integer, StatusCode> stringToEnum = new HashMap<>();

    static {
        for (StatusCode statusCode : values()) {
            stringToEnum.put(statusCode.code, statusCode);
        }
    }

    public static StatusCode fromCode(Integer code) {
        return stringToEnum.get(code);
    }
}
