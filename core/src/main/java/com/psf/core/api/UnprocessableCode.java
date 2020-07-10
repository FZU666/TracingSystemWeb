package com.psf.core.api;

/**
 * 枚举了一些无效字段错误
 */
public enum UnprocessableCode implements IErrorCode {
    MISSING("missing", "资源不存在"),
    MISSING_FIELD("missing_field", "资源上的必填字段尚未设置"),
    INVALID("invalid", "字段格式无效"),
    ALREADY_EXISTS("already_exists", "另一个资源与此字段具有相同的值");

    private final String code;
    private final String message;

    UnprocessableCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
