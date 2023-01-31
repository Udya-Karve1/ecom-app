package com.sk.rk.exception;

import lombok.Data;

@Data
public class BaseException extends Exception {
    private final int errorCode;
    private final String errorMessage;

    public BaseException(int errorCode, String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public BaseException(int errorCode, String errorMessage, Throwable throwable) {
        super(errorMessage, throwable);
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

}
