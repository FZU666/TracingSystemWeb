package com.psf.core.api;

/**
 * @author psf
 * @version 1.0
 */
public class Error {

    /**
     * 访问的资源
     */
    String resource;

    /**
     * 资源的某个字段
     */
    String field;

    /**
     * 验证错误代码
     */
    String code;

    public Error(String resource, String field, String code) {
        this.resource = resource;
        this.field = field;
        this.code = code;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
