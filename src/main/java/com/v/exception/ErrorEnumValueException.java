package com.v.exception;

/**
 * @anthor v
 * Create on 2018/12/6
 */
public class ErrorEnumValueException extends RuntimeException {

    public ErrorEnumValueException() {
    }

    public ErrorEnumValueException(String message) {
        super(message);
    }

    public ErrorEnumValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public ErrorEnumValueException(Throwable cause) {
        super(cause);
    }

    public ErrorEnumValueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
