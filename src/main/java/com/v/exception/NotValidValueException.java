package com.v.exception;

/**
 * @anthor v
 * Create on 2018/12/6
 */
public class NotValidValueException extends RuntimeException {

    public NotValidValueException() {
    }

    public NotValidValueException(String message) {
        super(message);
    }

    public NotValidValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotValidValueException(Throwable cause) {
        super(cause);
    }

    public NotValidValueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
