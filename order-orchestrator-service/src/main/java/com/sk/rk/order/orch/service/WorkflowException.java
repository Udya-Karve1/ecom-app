package com.sk.rk.order.orch.service;

import com.sk.rk.common.exception.BaseRunTimeException;

public class WorkflowException extends BaseRunTimeException {
    public WorkflowException(String message) {
        super(message);
    }
}
