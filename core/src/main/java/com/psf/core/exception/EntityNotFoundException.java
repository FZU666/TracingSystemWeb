package com.psf.core.exception;

/**
 * @author psf
 */
public class EntityNotFoundException extends BaseException {

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(BaseErrorEnum errorEnum) {
        super(errorEnum.getMessage());
    }
}
