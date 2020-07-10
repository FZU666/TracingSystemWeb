package com.psf.core.exception;

import com.psf.core.api.IErrorCode;
import com.psf.core.util.FieldUtil;
import io.micrometer.core.lang.Nullable;


/**
 * 断言处理类，用于抛出各种 API 异常
 *
 * @author psf
 */
public class Asserts {

    /**
     * check if an object has at least one non-null filed
     */
    public static void hasFiled(Object object) {
        try {
            if (!FieldUtil.hasNotNullFiled(object)) {
                Asserts.fail("all fields are empty");
            }
        } catch (IllegalAccessException e) {
            Asserts.fail("check field failed");
        }
    }

    public static void hasFiled(Object object, String message) {
        try {
            if (!FieldUtil.hasNotNullFiled(object)) {
                Asserts.fail(message);
            }
        } catch (IllegalAccessException e) {
            Asserts.fail(message);
        }
    }

    public static void notNull(@Nullable Object object) {
        if (object == null) {
            throw new EntityNotFoundException("Object doesn't exist");
        }
    }

    public static void unAuthorized(BaseErrorEnum errorEnum) {
        throw new AuthException(errorEnum.getMessage());
    }

    public static void unAuthorized(String message) {
        throw new AuthException(message);
    }

    public static void unAuthorized() {
        throw new AuthException("Full authentication is required to access this resource");
    }

    public static void forbidden(BaseErrorEnum errorEnum) {
        throw new AccessDeniedException(errorEnum.getMessage());
    }

    public static void forbidden(String message) {
        throw new AccessDeniedException(message);
    }

    public static void forbidden() {
        throw new AccessDeniedException("Access is denied");
    }

    public static void notFound(boolean condition) {
        if (condition) {
            throw new EntityNotFoundException("Entity is not found");
        }
    }

    public static void notFound(BaseErrorEnum errorEnum) { throw new EntityNotFoundException(errorEnum.getMessage()); }

    public static void notFound() { throw new EntityNotFoundException("not found"); }

    public static void notFound(String message) { throw new EntityNotFoundException(message); }

    public static void fail(BaseErrorEnum errorEnum) {
        throw new ApiException(errorEnum.getMessage());
    }

    public static void fail() {
        throw new ApiException("operation failed");
    }

    public static void fail(String message) {
        throw new ApiException(message);
    }

    public static void fail(IErrorCode errorCode) {
        throw new ApiException(errorCode);
    }
}
