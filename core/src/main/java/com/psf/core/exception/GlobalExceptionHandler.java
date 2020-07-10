package com.psf.core.exception;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONObject;
import com.psf.core.api.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

/**
 * 全局异常处理
 *
 * @author psf
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<Object> handle(ApiException e) {
        JSONObject jsonObject = new JSONObject();
        if (e.getErrorCode() != null) {
            jsonObject.put("message", e.getErrorCode());
            return ResponseEntity.badRequest().body(jsonObject);
        }
        jsonObject.put("message", e.getMessage());
        return ResponseEntity.badRequest().body(jsonObject);
    }

    @ExceptionHandler(value = AuthException.class)
    public ResponseEntity<ErrorResponse> handle(AuthException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorResponse.message(e.getMessage()));
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handle(AccessDeniedException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ErrorResponse.message(e.getMessage()));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.message(e.getMessage()));
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(StorageFileNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.message(e.getMessage()));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<JSONObject> requestExceptionHandler(MissingServletRequestParameterException exception) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", "required parameter '" + exception.getParameterName() + "' is not exist");
        return ResponseEntity.badRequest().body(jsonObject);
    }

    @ExceptionHandler({BindException.class, ConstraintViolationException.class})
    public ResponseEntity<ErrorResponse> validatorExceptionHandler(Exception e) {
        String msg = e instanceof BindException ? msgConverter(((BindException) e).getBindingResult())
            : msgConverter(((ConstraintViolationException) e).getConstraintViolations());
        return ResponseEntity.badRequest().body(ErrorResponse.message(msg));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodArgumentNotValidHandler(MethodArgumentNotValidException e) {
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        String message = "method argument does not valid";
        if (CollUtil.isNotEmpty(allErrors)) {
            ObjectError objectError = allErrors.get(0);
            message = objectError.getDefaultMessage();
        }
        return ResponseEntity.badRequest().body(ErrorResponse.message(message));
    }

    /**
     * 校验消息转换拼接
     */
    public static String msgConverter(BindingResult bindingResult) {
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        StringBuilder sb = new StringBuilder();
        fieldErrors.forEach(fieldError -> sb.append(fieldError.getDefaultMessage()).append(","));

        return sb.deleteCharAt(sb.length() - 1).toString().toLowerCase();
    }

    private String msgConverter(Set<ConstraintViolation<?>> constraintViolations) {
        StringBuilder sb = new StringBuilder();
        constraintViolations.forEach(violation -> sb.append(violation.getMessage()).append(","));

        return sb.deleteCharAt(sb.length() - 1).toString().toLowerCase();
    }
}
