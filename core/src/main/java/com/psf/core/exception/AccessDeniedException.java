package com.psf.core.exception;

/**
 * Access denied exception (403)
 * @author psf
 */
public class AccessDeniedException extends BaseException {

    public AccessDeniedException(String message) {
        super(message);
    }

    public AccessDeniedException(BaseErrorEnum errorEnum) {
        super(errorEnum);
    }
}
