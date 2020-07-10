package com.psf.core.exception;

/**
 * @author psf
 */
public class BaseException extends RuntimeException {

    public BaseException(String message) {
        super(message);
    }

    public BaseException(BaseErrorEnum errorEnum) {
        super(errorEnum.getMessage());
    }
}
