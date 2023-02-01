package com.sk.rk.service;

import com.sk.rk.exception.BaseRunTimeException;

public class WorkflowException extends BaseRunTimeException {
    public WorkflowException(String message) {
        super(message);
    }
}
