package com.psf.core.exception;

/**
 * Unauthorized exception (401)
 * @author psf
 */
public class AuthException extends BaseException {

    public AuthException(String message) {
        super(message);
    }

    public AuthException(BaseErrorEnum errorEnum) {
        super(errorEnum);
    }
}
